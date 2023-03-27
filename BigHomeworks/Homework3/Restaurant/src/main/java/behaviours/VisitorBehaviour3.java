package behaviours;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class VisitorBehaviour3 extends CyclicBehaviour {
    public void action() {
        ACLMessage msg = myAgent.receive();
        if (msg != null && msg.getContent().equals("ready")) {
            System.out.println("Food for " + myAgent.getLocalName() + " is ready");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myAgent.doDelete();
        } else {
            block();
        }
    }
}
