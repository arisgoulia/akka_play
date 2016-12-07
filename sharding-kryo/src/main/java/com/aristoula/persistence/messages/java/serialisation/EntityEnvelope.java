package com.aristoula.persistence.messages.java.serialisation;

public class EntityEnvelope {

    public long id;
    public CounterOp payload;
    private String someStuff = "124";
    private String otherThings="manoulamou";

    private EntityEnvelope() {
        
    }

    public EntityEnvelope(long id, CounterOp payload) {
        this.id = id;
        this.payload = payload;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("EntityEnvelope{");
        sb.append("id=").append(id);
        sb.append(", payload=").append(payload);
        sb.append(", someStuff='").append(someStuff).append('\'');
        sb.append(", otherThings='").append(otherThings).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
