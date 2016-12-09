package com.aristoula.persistence.custom;

import akka.actor.ExtendedActorSystem;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.serializers.CompatibleFieldSerializer;
import com.twitter.chill.KryoInstantiator;
import com.twitter.chill.akka.ConfiguredAkkaSerializer;

public class CustomKryoInstantiator extends KryoInstantiator {

    private static final long serialVersionUID = 2385046722532535264L;

    @Override
    public Kryo newKryo() {
        Kryo kryo = super.newKryo();
        kryo.setRegistrationRequired(true);
        return kryo;
    }

}
