package aristoula.routing;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.cluster.pubsub.DistributedPubSub;
import akka.cluster.pubsub.DistributedPubSubMediator;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class Subscriber extends UntypedActor {

    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    private String idToWatch;

    public Subscriber(String idToWatch) {
        this.idToWatch = idToWatch;
        ActorRef mediator = DistributedPubSub.get(getContext().system()).mediator();
        // subscribe to the topic named "content"
        mediator.tell(new DistributedPubSubMediator.Subscribe(idToWatch, getSelf()),
                      getSelf());
    }

    @Override
    public void onReceive(Object msg) {
        if (msg instanceof String)
            log.info("Got: {}", msg);
        else if (msg instanceof DistributedPubSubMediator.SubscribeAck)
            log.info("subscribing to topic " + idToWatch);
        else
            unhandled(msg);
    }
}
