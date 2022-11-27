package model.json.support

import akka.http.scaladsl.model.HttpRequest
import akka.http.scaladsl.unmarshalling.FromRequestUnmarshaller
import akka.stream.Materializer
import akka.stream.scaladsl.{Sink, Source}
import akka.util.ByteString
import skuber.json.format._
import play.api.libs.json.{JsValue, Json}
import skuber.Pod
import util.ActorSystemProvider

import scala.concurrent.{ExecutionContext, Future}
import scala.language.postfixOps

trait PodJsonSupport extends ActorSystemProvider {
  def podToJson(pod: Pod): JsValue = {
    Json.toJson(pod)
  }

  implicit val unmarshaller: FromRequestUnmarshaller[Pod] = new FromRequestUnmarshaller[Pod] {
    override def apply(value: HttpRequest)(implicit ec: ExecutionContext, materializer: Materializer): Future[Pod] = {
      getBody(value)
        .map[Pod](v => Json.parse(v.utf8String).as[Pod])
    }
  }

  private val getBodySource: HttpRequest => Source[ByteString, _] =
    _.entity dataBytes

  private val source2Seq: Source[ByteString, _] => Future[Seq[ByteString]] =
    _ runWith Sink.seq

  private val Seq2Str: Future[Seq[ByteString]] => Future[ByteString] =
    _ map (_ reduceOption (_ ++ _) getOrElse ByteString.empty)

  private val getBody: HttpRequest => Future[ByteString] =
    getBodySource andThen source2Seq andThen Seq2Str
}

