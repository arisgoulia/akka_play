package aristoula.route;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.cluster.pubsub.DistributedPubSub;
import akka.cluster.pubsub.DistributedPubSubMediator;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.persistence.UntypedPersistentActor;

public class Subscriber extends UntypedPersistentActor {

    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    private String idToWatch;

    public Subscriber(String topic) {
        this.idToWatch = topic;
        ActorRef mediator = DistributedPubSub.get(getContext().system()).mediator();
        // subscribe to the topic named "content"
        mediator.tell(new DistributedPubSubMediator.Subscribe(topic, getSelf()),
                      getSelf());
    }

    @Override
    public void onReceiveRecover(final Object msg) throws Exception {

    }

    @Override
    public void onReceiveCommand(final Object msg) throws Exception {

        if (msg instanceof String)
            log.info("Got: {}", msg);
        else if (msg instanceof DistributedPubSubMediator.SubscribeAck)
            log.info("subscribing to topic " + idToWatch);
        else
            unhandled(msg);

    }

    @Override
    public String persistenceId() {
        return null;
    }
}
