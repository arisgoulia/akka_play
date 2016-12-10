package com.aristoula.persistence.simplefiles;

import com.aristoula.messages.AnotherClass;
import com.aristoula.messages.ComplexClass;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Output;
import com.google.common.collect.Lists;

import java.io.FileOutputStream;
import java.io.IOException;


import static com.aristoula.persistence.simplefiles.KryoFactory.createKryo;

public class Writer {

    public static void main(String[] args) throws IOException {

        Kryo kryo = createKryo();

        Output output = new Output(new FileOutputStream("onlykryofile.bin"));
        kryo.writeClassAndObject(output, createComplexClass());
        output.close();

    }

    private static ComplexClass  createComplexClass(){

        return new ComplexClass("Will it work",
                                Lists.newArrayList(new AnotherClass("i am another class"), new AnotherClass("Another class indeed")));

    }

    /**
     *   return new ComplexClass("Will it work",
     Lists.newArrayList(new AnotherClass("i am another class"), new AnotherClass("Another class indeed")),
     Lists.newArrayList(new AnotherClass("Extra Field"), new AnotherClass("Extra Extra field")));
     */

}
