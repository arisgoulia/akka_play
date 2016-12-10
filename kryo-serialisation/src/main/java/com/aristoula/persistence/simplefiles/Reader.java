package com.aristoula.persistence.simplefiles;

import com.aristoula.messages.ClassWithFinalFields;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;

import java.io.FileInputStream;
import java.io.IOException;


import static com.aristoula.persistence.simplefiles.KryoFactory.createKryo;

public class Reader {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Kryo kryo = createKryo();

        Input input = new Input(new FileInputStream("onlykryofile.bin"));
        System.out.println(kryo.readClassAndObject(input));

        input.close();

    }


}
