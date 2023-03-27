package agents;

import behaviours.RegisterInDFBehaviour;
import jade.core.Agent;
import jade.domain.DFService;

public class BasicAgent extends Agent {
    protected Object[] startupArgs;

    @Override
    protected void setup() {
        startupArgs = getArguments();
        // TODO: use logging
        System.out.println("Agent " + getAID().getName() + " created");
        addBehaviour(new RegisterInDFBehaviour(this, (String) startupArgs[0]));
    }

    @Override
    protected void takeDown() {
        try {
            DFService.deregister(this);
            System.out.println(getLocalName() + " deregistered");
        } catch (Exception ex) {
            System.out.println("Failed deregistering in DF!");
            ex.printStackTrace();
        }
        System.out.println("Agent " + getAID().getName() + " terminated");
    }
}
