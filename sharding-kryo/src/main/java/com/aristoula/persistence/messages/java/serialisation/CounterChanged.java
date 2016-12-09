package com.aristoula.persistence.messages.java.serialisation;

import java.io.Serializable;

public class CounterChanged implements Serializable {

    private static final long serialVersionUID = 2L;
    public int delta;
    private int  meme = 1234;

    private CounterChanged(){};

    public CounterChanged(int delta) {
        this.delta = delta;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CounterChanged{");
        sb.append("delta=").append(delta);
        sb.append(", meme=").append(meme);
        sb.append('}');
        return sb.toString();
    }

}
