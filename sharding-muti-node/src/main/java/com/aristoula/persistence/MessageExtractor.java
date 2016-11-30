package com.aristoula.persistence;

import akka.cluster.sharding.ShardRegion;
import com.aristoula.persistence.messages.EntityEnvelope;
import com.aristoula.persistence.messages.Get;

public class MessageExtractor implements ShardRegion.MessageExtractor {

        @Override
        public String entityId(Object message) {
            if (message instanceof EntityEnvelope)
                return String.valueOf(((EntityEnvelope) message).id);
            else if (message instanceof Get)
                return String.valueOf(((Get) message).counterId);
            else
                return null;
        }

        @Override
        public Object entityMessage(Object message) {
            if (message instanceof EntityEnvelope)
                return ((EntityEnvelope) message).payload;
            else
                return message;
        }

        @Override
        public String shardId(Object message) {
            int numberOfShards = 100;
            if (message instanceof EntityEnvelope) {
                long id = ((EntityEnvelope) message).id;
                return String.valueOf(id % numberOfShards);
            } else if (message instanceof Get) {
                long id = ((Get) message).counterId;
                return String.valueOf(id % numberOfShards);
            } else {
                return null;
            }
        }

}
