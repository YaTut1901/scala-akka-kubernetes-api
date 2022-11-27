package api

import akka.http.scaladsl.ServerBuilder
import akka.http.scaladsl.server.Route
import service.KubernetesService
import skuber.ObjectResource
import util.TypedActorSystemProvider

trait KubernetesController[E <: ObjectResource] extends TypedActorSystemProvider {
  var routes: Array[Route]
  var kubernetesService: KubernetesService[E]
  var next: Option[KubernetesController[_ <: ObjectResource]]

  def get(): KubernetesController[E]
  def getAll(): KubernetesController[E]
  def post(): KubernetesController[E]
  def patch(): KubernetesController[E]
  def delete(): KubernetesController[E]

  def bind(builder: ServerBuilder): Unit = {
    routes.foreach(e => {
      builder.bind(e)
    })

    if (next.isDefined) {
      next.get.bind(builder)
    }
  }}
