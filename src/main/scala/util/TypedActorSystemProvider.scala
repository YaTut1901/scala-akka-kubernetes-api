package util

import akka.actor.typed.ActorSystem
import akka.actor.typed.javadsl.Behaviors

trait TypedActorSystemProvider {
  implicit val actorSystem: ActorSystem[Nothing] = ActorSystem(Behaviors.empty, "akka-http")
}
