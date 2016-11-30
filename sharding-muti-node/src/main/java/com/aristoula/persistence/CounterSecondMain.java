package com.aristoula.persistence;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.cluster.sharding.ClusterSharding;
import akka.cluster.sharding.ClusterShardingSettings;
import akka.japi.Option;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class CounterSecondMain {

    private static ActorRef getSelf() {
        return null;
    }

    public static void main (String[] args) throws InterruptedException {

        final String port = args.length > 0 ? args[0] : "0";
        final Config config = ConfigFactory.parseString("akka.remote.netty.tcp.port=" + port).
                withFallback(ConfigFactory.load());

        ActorSystem system = ActorSystem.create("ClusterSystem", config);

        //#counter-start
        Option<String> roleOption = Option.none();
        ClusterShardingSettings settings = ClusterShardingSettings.create(system);
        ActorRef startedCounterRegion = ClusterSharding.get(system).start("Counter",
                                                                          Props.create(Counter.class),
                                                                          settings,
                                                                          new MessageExtractor());

    }

}
