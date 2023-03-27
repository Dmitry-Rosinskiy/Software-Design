import jade.Boot;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;

import models.*;
import models.lists.*;

public class Main {
    private static void prepareRestaurant() {
        Menu.read("input files/menu_dishes.txt");
        EquipmentTypeList.read("input files/equipment_type.txt");
        EquipmentList.read("input files/equipment.txt");
        CookerList.read("input files/cookers.txt");
        ProductTypeList.read("input files/product_types.txt");
        ProductList.read("input files/products.txt");
        OperationTypeList.read("input files/operation_types.txt");
        VisitorList.read("input files/visitors_orders.txt");
        DishCardList.read("input files/dish_cards.txt");
    }

    public static void main(String[] args) {
        prepareRestaurant();

        Boot.main(new String[] {"-gui"});
        Runtime rt = Runtime.instance();
        Profile p = new ProfileImpl();
        p.setParameter(Profile.CONTAINER_NAME, "Agents Container");
        p.setParameter(Profile.MAIN_HOST, "localhost");
        ContainerController cc = rt.createAgentContainer(p);

        try {
            Object[] agentArgs;
            AgentController ac;
            agentArgs = new Object[] {"supervisor"};
            ac = cc.createNewAgent("supervisor", "agents.supervisors.SupervisorAgent", agentArgs);
            ac.start();
            for (Visitor visitor : VisitorList.getInstance().visitors_orders) {
                agentArgs = new Object[] {"visitor"};
                ac = cc.createNewAgent(visitor.vis_name(), "agents.endpoints.VisitorAgent", agentArgs);
                ac.start();
            }
            for (Equipment equipment : EquipmentList.getInstance().equipment) {
                agentArgs = new Object[] {"equipment"};
                ac = cc.createNewAgent(equipment.equip_name(), "agents.EquipmentAgent", agentArgs);
                ac.start();
            }
            for (Cooker cooker : CookerList.getInstance().cookers) {
                agentArgs = new Object[] {"cooker"};
                ac = cc.createNewAgent(cooker.cook_name(), "agents.endpoints.CookerAgent", agentArgs);
                ac.start();
            }
        } catch (StaleProxyException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
}
