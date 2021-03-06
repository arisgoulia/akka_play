/**
 * Copyright (C) 2009-2016 Lightbend Inc. <http://www.lightbend.com>
 */

package com.aristoula.persistence;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.cluster.sharding.ClusterSharding;
import akka.cluster.sharding.ClusterShardingSettings;
import com.aristoula.persistence.messages.java.serialisation.CounterOp;
import com.aristoula.persistence.messages.java.serialisation.EntityEnvelope;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import scala.concurrent.duration.Duration;
import scala.concurrent.duration.FiniteDuration;

import java.util.concurrent.TimeUnit;

// Doc code, compile only
public class CounterKryoOneMain {

  private static ActorRef getSelf() {
    return null;
  }

  public static void main (String[] args) throws InterruptedException {

    final String port = args.length > 0 ? args[0] : "0";
    final Config config = ConfigFactory.parseString("akka.remote.netty.tcp.port=" + port).
                                               withFallback(ConfigFactory.load());

    ActorSystem system = ActorSystem.create("ClusterSystem", config);

    ClusterShardingSettings settings = ClusterShardingSettings.create(system);
    final ActorRef startedCounterRegion = ClusterSharding.get(system).start("Counter",
                                                                      Props.create(Counter.class),
                                                                      settings,
                                                                      new MessageExtractor());

    Thread.sleep(10000L);
    //#counter-start

    final FiniteDuration interval = Duration.create(2, TimeUnit.SECONDS);

    for(int i = 0; i<100 ; i ++) {

        final int j = i;

      system.scheduler().schedule(interval, interval, new Runnable() {
        public void run() {

            EntityEnvelope entityEnvelope = new EntityEnvelope((long)j, CounterOp.INCREMENT);

            startedCounterRegion.tell(entityEnvelope, ActorRef.noSender());


        }
      }, system.dispatcher());
    }

  }



}
