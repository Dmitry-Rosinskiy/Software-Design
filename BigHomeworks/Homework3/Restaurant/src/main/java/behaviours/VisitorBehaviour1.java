package behaviours;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import models.Order;
import models.lists.VisitorList;

import java.util.Arrays;
import java.util.stream.Collectors;

public class VisitorBehaviour1 extends Behaviour {
    private boolean supervisorFound = false;
    public void action() {
        DFAgentDescription template = new DFAgentDescription();
        ServiceDescription sd = new ServiceDescription();
        sd.setType("supervisor");
        template.addServices(sd);
        try {
            DFAgentDescription[] result = DFService.search(myAgent, template);
            var supervisorAgents = new AID[result.length];
            for (int i = 0; i < result.length; ++i) {
                supervisorAgents[i] = result[i].getName();
                System.out.println(myAgent.getLocalName() + " found " + supervisorAgents[i].getLocalName());
                ACLMessage msg = new ACLMessage(ACLMessage.PROPOSE);
                msg.addReceiver(new AID(supervisorAgents[i].getLocalName(), AID.ISLOCALNAME));
                String orderDishes = Arrays.stream(getOrders(myAgent.getLocalName()))
                        .map(order -> order.menu_dish() + "")
                        .collect(Collectors.joining(", "));
                msg.setContent(orderDishes);
                myAgent.send(msg);
                supervisorFound = true;
            }}
        catch (FIPAException fe) {
            fe.printStackTrace();
        }
    }

    public boolean done() {
        return supervisorFound;
    }

    private Order[] getOrders(String visitorName) {
        var allOrders = VisitorList.getInstance().visitors_orders;
        for (var visitorsOrders : allOrders) {
            if (visitorsOrders.vis_name().equals(visitorName)) {
                return visitorsOrders.vis_ord_dishes();
            }
        }
        return new Order[] {};
    }
}
