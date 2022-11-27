package api.impl

import akka.http.scaladsl.server.Directives.{as, complete, entity, onComplete, pathPrefix}
import akka.http.scaladsl.server.PathMatchers.Remaining
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.directives.MethodDirectives
import api.KubernetesController
import model.json.support.PodJsonSupport
import service.KubernetesService
import service.impl.PodService
import skuber.{ObjectResource, Pod}
import util.ActorSystemProvider

import scala.util.{Failure, Success}

class PodController(override var routes: Array[Route] = Array(),
                    override var kubernetesService: KubernetesService[Pod] = new PodService,
                    override var next: Option[KubernetesController[_ <: ObjectResource]] = Option.empty)
  extends KubernetesController[Pod]
  with PodJsonSupport
  with ActorSystemProvider {

  override def get(): KubernetesController[Pod] = {
    val getRoute: Route =
      MethodDirectives.get {
        pathPrefix("pod") {
          pathPrefix(Remaining) { name =>
            onComplete(kubernetesService.get(name)) {
              case Success(pod) => complete(podToJson(pod).toString())
              case Failure(e) =>
                complete(e.toString)
                throw e
            }
          }
        }
      }

    routes +:= getRoute
    this
  }

  override def getAll(): KubernetesController[Pod] = ???

  override def post(): KubernetesController[Pod] = {
    val postRoute: Route =
      MethodDirectives.post {
        pathPrefix("pod") {
          entity(as[Pod]) { pod =>
            print(pod)
            complete("res")
          }
        }
      }

    routes +:= postRoute
    this
  }

  override def patch(): KubernetesController[Pod] = ???

  override def delete(): KubernetesController[Pod] = ???
}
