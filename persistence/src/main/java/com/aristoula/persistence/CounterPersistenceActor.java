package com.aristoula.persistence;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.japi.Procedure;
import akka.persistence.RecoveryCompleted;
import akka.persistence.SnapshotOffer;
import akka.persistence.UntypedPersistentActor;

import java.io.Serializable;

public class CounterPersistenceActor extends UntypedPersistentActor {

    private int counter = 0;

    public static void main(String... args) throws Exception {

        final ActorSystem system = ActorSystem.create("example");
        final ActorRef persistentActor =
                system.actorOf(Props.create(CounterPersistenceActor.class), "persistentActor-4-java");

        persistentActor.tell(new ModifyCounter(1), null);
        persistentActor.tell(new ModifyCounter(1), null);
        persistentActor.tell(new ModifyCounter(1), null);
        persistentActor.tell("snap", null);

        Thread.sleep(10000);
        system.terminate();
    }

    @Override
    public String persistenceId() {
        return "sample-counter-persistor-1";
    }

    @Override
    public void onReceiveRecover(Object msg) {
        if (msg instanceof ModifyCounter) {
            System.out.println(String.format("Received Recover ModifyCounter %s", msg));
            this.counter += ((ModifyCounter) msg).getDelta();
        } else if(msg instanceof SnapshotOffer) {
            System.out.println(String.format("Received SnapshotOffer %s", msg));
            this.counter = (int)((SnapshotOffer)msg).snapshot();
        } else if (msg instanceof RecoveryCompleted) {
            System.out.println(String.format("Recovery completed. final State is %s", this.counter));
        } else {
            unhandled(msg);
        }
    }

    @Override
    public void onReceiveCommand(Object msg) {
        if (msg instanceof ModifyCounter) {
            ModifyCounter modifyCounter = (ModifyCounter) msg;
            persist(modifyCounter, new Procedure<ModifyCounter>() {
                public void apply(ModifyCounter evt) throws Exception {
                    counter += evt.getDelta();
                }
            });
        } else if (msg.equals("snap")) {
            saveSnapshot(this.counter);
        } else if (msg.equals("deleteEvents")){
        }
    }

    static class ModifyCounter implements Serializable {

        private static final long serialVersionUID = -4201708924505964868L;
        private final int delta;

        public ModifyCounter(int delta) {
            this.delta = delta;
        }

        public int getDelta() {
            return this.delta;
        }

    }
}

