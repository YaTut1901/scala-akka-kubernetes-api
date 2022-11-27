import akka.http.scaladsl.Http
import api.KubernetesController
import api.impl.PodController
import skuber.ObjectResource
import util.TypedActorSystemProvider

object Main extends App with TypedActorSystemProvider {
  val controller: KubernetesController[_ <: ObjectResource] = new PodController

  controller.get()
    .post()
    .bind(Http().newServerAt("127.0.0.1", 8080))
}