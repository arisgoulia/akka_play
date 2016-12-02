package com.aristoula.persistence;

import akka.cluster.sharding.ShardRegion;
import com.aristoula.persistence.messages.proto.Messages;

public class MessageExtractor implements ShardRegion.MessageExtractor {


    @Override
    public String entityId(Object message) {

        System.out.println("message received " + message);

        if (message instanceof Messages.EntityEnvelope)
            return String.valueOf(((Messages.EntityEnvelope) message).getId());
        else if (message instanceof Messages.Get)
            return String.valueOf(((Messages.Get) message).getCounterId());
        else
            return null;
    }

    @Override
    public Object entityMessage(Object message) {
        if (message instanceof Messages.EntityEnvelope)
            return ((Messages.EntityEnvelope) message).getPayload();
        else
            return message;
    }

    @Override
    public String shardId(Object message) {
        int numberOfShards = 100;
        if (message instanceof Messages.EntityEnvelope) {
            long id = ((Messages.EntityEnvelope) message).getId();
            return String.valueOf(id % numberOfShards);
        } else if (message instanceof Messages.Get) {
            long id = ((Messages.Get) message).getCounterId();
            return String.valueOf(id % numberOfShards);
        } else {
            return null;
        }
    }

}
