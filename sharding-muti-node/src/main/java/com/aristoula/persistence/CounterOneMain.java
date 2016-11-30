/**
 * Copyright (C) 2009-2016 Lightbend Inc. <http://www.lightbend.com>
 */

package com.aristoula.persistence;

import akka.actor.AbstractActor;
import akka.actor.ActorInitializationException;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.OneForOneStrategy;
import akka.actor.PoisonPill;
import akka.actor.Props;
import akka.actor.ReceiveTimeout;
import akka.actor.SupervisorStrategy;
import akka.actor.Terminated;
import akka.actor.UntypedActor;
import akka.cluster.Cluster;
import akka.cluster.sharding.ClusterSharding;
import akka.cluster.sharding.ClusterShardingSettings;
import akka.cluster.sharding.ShardRegion;
import akka.japi.Option;
import akka.japi.Procedure;
import akka.japi.pf.DeciderBuilder;
import akka.japi.pf.ReceiveBuilder;
import akka.persistence.UntypedPersistentActor;
import scala.concurrent.duration.Duration;


import static java.util.concurrent.TimeUnit.SECONDS;

// Doc code, compile only
public class ClusterShardingTest {

  private static ActorSystem system = ActorSystem.create("SharderTest");

  private static ActorRef getSelf() {
    return null;
  }

  public static void main (String[] args) {
    //#counter-extractor

    //#counter-extractor

    //#counter-start
    Option<String> roleOption = Option.none();
    ClusterShardingSettings settings = ClusterShardingSettings.create(system);
    ActorRef startedCounterRegion = ClusterSharding.get(system).start("Counter",
                                                                      Props.create(Counter.class),
                                                                      settings,
                                                                      new MessageExtractor());
    //#counter-start

    //#counter-usage
    ActorRef counterRegion = ClusterSharding.get(system).shardRegion("Counter");
    counterRegion.tell(new Counter.Get(123), getSelf());

    counterRegion.tell(new Counter.EntityEnvelope(123,
                                                  Counter.CounterOp.INCREMENT), getSelf());
    counterRegion.tell(new Counter.Get(123), getSelf());
    //#counter-usage

    //#counter-supervisor-start
    ClusterSharding.get(system).start("SupervisedCounter",
                                      Props.create(CounterSupervisor.class), settings, messageExtractor);
    //#counter-supervisor-start
  }



}
