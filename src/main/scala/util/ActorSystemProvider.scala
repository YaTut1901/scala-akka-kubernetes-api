package util

import akka.actor.ActorSystem

import scala.concurrent.ExecutionContextExecutor

trait ActorSystemProvider {
  implicit val system: ActorSystem = ActorSystem()
  implicit val dispatcher: ExecutionContextExecutor = system.dispatcher

}
