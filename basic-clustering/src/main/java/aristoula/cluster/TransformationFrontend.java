package aristoula.cluster;

import akka.actor.ActorRef;
import akka.actor.Terminated;
import akka.actor.UntypedActor;
import static aristoula.cluster.TransformationMessages.*;


import java.util.ArrayList;
import java.util.List;

//#frontend
public class TransformationFrontend extends UntypedActor {

  List<ActorRef> backends = new ArrayList<ActorRef>();
  int jobCounter = 0;

  @Override
  public void onReceive(Object message) {
    if ((message instanceof TransformationJob) && backends.isEmpty()) {
      TransformationJob job = (TransformationJob) message;
      getSender().tell(
          new JobFailed("Service unavailable, try again later", job),
          getSender());

    } else if (message instanceof TransformationJob) {
      TransformationJob job = (TransformationJob) message;
      jobCounter++;
      backends.get(jobCounter % backends.size())
          .forward(job, getContext());

    } else if (message.equals(BACKEND_REGISTRATION)) {
      getContext().watch(getSender());
      backends.add(getSender());

    } else if (message instanceof Terminated) {
      Terminated terminated = (Terminated) message;
      backends.remove(terminated.getActor());

    } else {
      unhandled(message);
    }
  }

}
//#frontend
