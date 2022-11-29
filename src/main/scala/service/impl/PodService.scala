package service.impl

import service.KubernetesService
import skuber._
import skuber.json.format.podFormat

import scala.concurrent.Future

class PodService extends KubernetesService[Pod] {
  override def persist(entity: Pod): Future[Pod] = {
    k8s.create(entity)
  }

  override def get(name: String): Future[Pod] = {
    k8s.get[Pod](name)
  }
}
