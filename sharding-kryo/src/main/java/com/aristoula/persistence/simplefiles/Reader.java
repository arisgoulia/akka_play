package com.aristoula.persistence.simplefiles;

import com.aristoula.persistence.messages.java.serialisation.CounterChanged;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;

import java.io.FileInputStream;
import java.io.IOException;

public class Reader {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Kryo kryo = new Kryo();

        Input input = new Input(new FileInputStream("file.bin"));
        CounterChanged another = kryo.readObject(input, CounterChanged.class);

        input.close();

    }


}
