package com.aristoula.persistence;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class CounterPersistenceActorMain {

    public static void main(String[] args) throws InterruptedException {

        final ActorSystem system = ActorSystem.create("example");
        final ActorRef persistentActor =
                system.actorOf(Props.create(CounterPersistenceActor.class), "persistentActor-4-java");

        persistentActor.tell(new CounterPersistenceActor.ModifyCounter(1), null);
        persistentActor.tell(new CounterPersistenceActor.ModifyCounter(1), null);
        persistentActor.tell(new CounterPersistenceActor.ModifyCounter(1), null);
        persistentActor.tell("snap", null);

        persistentActor.tell(new CounterPersistenceActor.ModifyCounter(1), null);
        persistentActor.tell(new CounterPersistenceActor.ModifyCounter(1), null);

        Thread.sleep(10000);
        system.terminate();
    }

}

