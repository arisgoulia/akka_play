package aristoula.routing;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akka.cluster.pubsub.DistributedPubSub;
import akka.cluster.pubsub.DistributedPubSubMediator;

public class Publisher extends UntypedActor {

    private final String topicToSend;

    public Publisher(String topicToSend){
        this.topicToSend = topicToSend;
    }

    // activate the extension
    ActorRef mediator = DistributedPubSub.get(getContext().system()).mediator();

    public void onReceive(Object msg) {
        if (msg instanceof String) {
            String in = (String) msg;
            String out = in.toUpperCase();
            this.mediator.tell(new DistributedPubSubMediator.Publish(this.topicToSend, out),
                               getSelf());
        } else {
            unhandled(msg);
        }
    }
}
