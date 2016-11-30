package aristoula.route;

import akka.actor.ActorRef;
import akka.cluster.pubsub.DistributedPubSub;
import akka.cluster.pubsub.DistributedPubSubMediator;
import akka.persistence.UntypedPersistentActorWithAtLeastOnceDelivery;

public class Publisher extends UntypedPersistentActorWithAtLeastOnceDelivery {

    private final String topicToSend;
    private final String persistenceId;
    // activate the extension
    ActorRef mediator = DistributedPubSub.get(getContext().system()).mediator();

    public Publisher(String persistenceId, String topic) {
        this.persistenceId = persistenceId;
        this.topicToSend = topic;
    }

    @Override
    public void onReceiveRecover(final Object msg) throws Exception {


    }

    @Override
    public void onReceiveCommand(final Object msg) throws Exception {

        if (msg instanceof String) {
            String in = (String) msg;
            String out = in.toUpperCase();
            this.mediator.tell(new DistributedPubSubMediator.Publish(this.topicToSend, out),
                               getSelf());
        } else {
            unhandled(msg);
        }

    }

    @Override
    public String persistenceId() {
        return this.persistenceId;
    }
}
