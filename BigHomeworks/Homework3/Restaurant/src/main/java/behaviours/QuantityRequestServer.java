package behaviours;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class QuantityRequestServer extends CyclicBehaviour {
    private final double productQnt;

    public QuantityRequestServer(double productQnt) {
        this.productQnt = productQnt;
    }

    @Override
    public void action() {
        MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.CFP);

        ACLMessage msg = myAgent.receive(mt);
        if (msg != null) {
            ACLMessage reply = msg.createReply();

            if (productQnt > 0) {
                reply.setPerformative(ACLMessage.PROPOSE);
                reply.setContent(String.valueOf(productQnt));
            } else {
                reply.setPerformative(ACLMessage.REFUSE);
                reply.setContent("not-available");
            }
            myAgent.send(reply);
        } else {
            block();
        }
    }
}
