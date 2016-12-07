package com.aristoula.persistence.custom;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.serializers.CompatibleFieldSerializer;
import com.twitter.chill.KryoInstantiator;

public class CustomKryoInstantiator extends KryoInstantiator {

    private static final long serialVersionUID = 2385046722532535264L;

    @Override
    public Kryo newKryo() {

        Kryo kryo = super.newKryo();

        return kryo;

    }
}
