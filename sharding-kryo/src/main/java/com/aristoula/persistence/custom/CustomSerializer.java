package com.aristoula.persistence.custom;

import akka.actor.ExtendedActorSystem;
import com.twitter.chill.KryoInstantiator;
import com.twitter.chill.akka.AkkaSerializer;

public class CustomSerializer extends AkkaSerializer {

    public CustomSerializer(final ExtendedActorSystem system) {
        super(system);
    }

    @Override
    public KryoInstantiator kryoInstantiator() {
        return new CustomKryoInstantiator();
    }
}
