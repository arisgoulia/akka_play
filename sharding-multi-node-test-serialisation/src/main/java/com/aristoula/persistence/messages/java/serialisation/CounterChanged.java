package com.aristoula.persistence.messages.java.serialisation;

public class CounterChanged {
    final public int delta;

    public CounterChanged(int delta) {
        this.delta = delta;
    }
}
