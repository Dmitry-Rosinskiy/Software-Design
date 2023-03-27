package behaviours;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public class RegisterInDFBehaviour extends OneShotBehaviour {
    private final String serviceType;
   // private String ownership;
    public RegisterInDFBehaviour(Agent agent, String serviceType) {
        super(agent);
        this.serviceType = serviceType;
        //this.ownership = ownership;
    }

    @Override
    public void action() {
        ServiceDescription sd = new ServiceDescription();
        sd.setType(serviceType);
        //sd.setOwnership(ownership);
        sd.setName(myAgent.getName());
        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(myAgent.getAID());
        dfd.addServices(sd);
        try {
            DFAgentDescription[] dfds = DFService.search(myAgent, dfd);
            if (dfds.length > 0) {
                DFService.deregister(myAgent, dfd);
            }
            DFService.register(myAgent, dfd);
            System.out.println(myAgent.getLocalName() + " is registered");
        } catch (Exception ex) {
            System.out.println("Failed registering in DF! Shutting down...");
            ex.printStackTrace();
            myAgent.doDelete();
        }
    }
}
