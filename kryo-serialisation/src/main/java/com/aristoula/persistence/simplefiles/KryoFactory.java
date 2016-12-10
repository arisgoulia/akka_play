package com.aristoula.persistence.simplefiles;

import com.aristoula.messages.AnotherClass;
import com.aristoula.messages.ComplexClass;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.serializers.TaggedFieldSerializer;

import java.util.ArrayList;

import org.objenesis.strategy.StdInstantiatorStrategy;

public class KryoFactory {

    public static Kryo createKryo(){

        Kryo kryo = new Kryo();
        kryo.setDefaultSerializer(TaggedFieldSerializer.class);
        kryo.getTaggedFieldSerializerConfig().setIgnoreUnknownTags(true);
        kryo.setInstantiatorStrategy(new Kryo.DefaultInstantiatorStrategy(new StdInstantiatorStrategy()));
        kryo.setRegistrationRequired(true);
        kryo.register(ArrayList.class,1);
        kryo.register(ComplexClass.class,2);
        kryo.register(AnotherClass.class,3);
//        kryo.setRegistrationRequired(true);
//
//        kryo.register(AnotherClass.class);
//        kryo.register(ClassWithFinalFields.class);
//        kryo.register(ComplexClass.class);

        return kryo;

    }
}
