package com.aristoula.persistence.simplefiles;

import com.aristoula.persistence.messages.java.serialisation.CounterChanged;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class JavaSerialisationWriter  {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        ObjectOutputStream fileOutputStream = new ObjectOutputStream(new FileOutputStream("file.bin"));
        CounterChanged someObject = new CounterChanged(1000);
        fileOutputStream.writeObject(someObject);
        fileOutputStream.close();

    }

}
