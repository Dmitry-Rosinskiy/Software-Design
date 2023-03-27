package behaviours;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;


public class VisitorBehaviour2 extends Behaviour {
    private boolean gotTime = false;
    public void action() {
        ACLMessage msg = myAgent.receive();
        if (msg != null) {
            System.out.println(myAgent.getLocalName() + "'s order is " + msg.getContent() + " hours long");
            gotTime = true;
        }
    }

    public boolean done() {
        return gotTime;
    }
}
