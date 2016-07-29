package aristoula.routing;

import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class SubscriberMain {


    public static void main(String[] args) {
        // Override the configuration of the port when specified as program argument

        final String port = args[0];
        final String topic = args[1];

        final Config config = ConfigFactory.parseString("akka.remote.netty.tcp.port=" + port).
                withFallback(ConfigFactory.parseString("akka.cluster.roles = [backend]")).
                                                   withFallback(ConfigFactory.load());

        ActorSystem system = ActorSystem.create("ClusterSystem", config);

        system.actorOf(Props.create(Subscriber.class,topic),"subscriberForTopic"+topic);

    }


}
