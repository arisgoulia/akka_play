package com.aristoula.persistence;

import akka.actor.PoisonPill;
import akka.actor.ReceiveTimeout;
import akka.actor.UntypedActor;
import akka.cluster.sharding.ShardRegion;
import com.aristoula.persistence.messages.java.serialisation.CounterChanged;
import com.aristoula.persistence.messages.java.serialisation.CounterOp;
import com.aristoula.persistence.messages.java.serialisation.Get;
import scala.concurrent.duration.Duration;


import static java.util.concurrent.TimeUnit.SECONDS;

public class Counter extends UntypedActor {

    int count = 0;

    @Override
    public void onReceive(final Object msg) throws Exception {

        if (msg instanceof Get)
            getSender().tell(count, getSelf());
        else if (msg == CounterOp.INCREMENT)
            updateState(new CounterChanged(+1));
        else if (msg == CounterOp.DECREMENT)
            updateState(new CounterChanged(-1));
        else if (msg.equals(ReceiveTimeout.getInstance()))
            getContext().parent().tell(
                    new ShardRegion.Passivate(PoisonPill.getInstance()), getSelf());
        else
            unhandled(msg);

    }

    void updateState(CounterChanged event) {
        System.out.print("Received CounterChanged event");
        count += event.delta;
    }

    @Override
    public void preStart() throws Exception {
        super.preStart();
        context().setReceiveTimeout(Duration.create(120, SECONDS));
    }


}

