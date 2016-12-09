package com.aristoula.persistence.custom;

import akka.actor.ExtendedActorSystem;
import com.twitter.chill.KryoInstantiator;
import com.twitter.chill.akka.AkkaSerializer;
import com.twitter.chill.akka.ConfiguredAkkaSerializer;

public class CustomSerializer extends ConfiguredAkkaSerializer {

    public CustomSerializer(final ExtendedActorSystem system) {
        super(system);
    }

    @Override
    public KryoInstantiator kryoInstantiator() {
        return super.kryoInstantiator();
    }

}
