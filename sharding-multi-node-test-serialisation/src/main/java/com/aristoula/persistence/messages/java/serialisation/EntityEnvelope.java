package com.aristoula.persistence.messages.java.serialisation;

public class EntityEnvelope {

    final public long id;
    final public CounterOp payload;

    public EntityEnvelope(long id, CounterOp payload) {
        this.id = id;
        this.payload = payload;
    }
}
