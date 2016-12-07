package com.aristoula.persistence.messages.java.serialisation;

public class Get {
    
    public long counterId;

    private Get(){

    }

    public Get(long counterId) {
        this.counterId = counterId;
    }
}
