package com.aristoula.persistence.messages;

public class CounterChanged {
    final public int delta;

    public CounterChanged(int delta) {
        this.delta = delta;
    }
}
