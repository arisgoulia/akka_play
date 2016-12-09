package com.aristoula.persistence.simplefiles;

import com.aristoula.persistence.messages.java.serialisation.CounterChanged;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class JavaSerialisationReader {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("file.bin"));
        CounterChanged changed  = (CounterChanged)inputStream.readObject();
        System.out.println(changed);

    }
}
