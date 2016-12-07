package com.aristoula.persistence.messages.java.serialisation;

public class CounterChanged {

    public int delta;

    private CounterChanged(){};

    public CounterChanged(int delta) {
        this.delta = delta;
    }
}
