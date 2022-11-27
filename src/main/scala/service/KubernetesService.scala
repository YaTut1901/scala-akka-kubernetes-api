package service

import skuber.api.client.KubernetesClient
import skuber.{ObjectResource, k8sInit}
import util.ActorSystemProvider

import scala.concurrent.Future

trait KubernetesService[T <: ObjectResource] extends ActorSystemProvider {
  val k8s: KubernetesClient = k8sInit
  def persist(entity: T): Future[T]
  def get(name: String): Future[T]
}
