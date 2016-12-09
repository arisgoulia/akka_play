package com.aristoula.persistence.simplefiles;

import com.aristoula.persistence.messages.java.serialisation.CounterChanged;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Output;

import java.io.FileOutputStream;
import java.io.IOException;

public class Writer {

    public static void main(String[] args) throws IOException {

        Kryo kryo = new Kryo();

        Output output = new Output(new FileOutputStream("file.bin"));
        CounterChanged someObject = new CounterChanged(1000);
        kryo.writeObject(output, someObject);
        output.close();

    }

}
