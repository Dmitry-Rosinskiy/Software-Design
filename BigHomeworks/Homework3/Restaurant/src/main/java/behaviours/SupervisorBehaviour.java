package behaviours;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;

public class SupervisorBehaviour extends CyclicBehaviour {
    public void action() {
        ACLMessage msg = myAgent.receive();
        if (msg != null) {
            System.out.println("We need to prepare " + msg.getContent() + " for " + msg.getSender().getLocalName());
            try {
                ContainerController cc = myAgent.getContainerController();
                var args = new Object[] {"dish", msg.getSender().getLocalName()};
                AgentController ac = cc.createNewAgent(msg.getContent(), "agents.OrderAgent", args);
                ac.start();
            } catch (StaleProxyException e) {
                e.printStackTrace();
            }
        } else {
            block();
        }
    }
}
