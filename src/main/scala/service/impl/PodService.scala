package service.impl

import service.KubernetesService
import skuber._
import skuber.json.format.podFormat

import scala.concurrent.Future
import scala.util.Try

class PodService extends KubernetesService[Pod] {
  override def persist(entity: Pod): Future[Pod] = {
    Future.fromTry(Try(entity))
  }

  override def get(name: String): Future[Pod] = {
    k8s.get[Pod](name)
  }
}
