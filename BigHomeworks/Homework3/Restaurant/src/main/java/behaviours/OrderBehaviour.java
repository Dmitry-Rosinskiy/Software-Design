package behaviours;

import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import models.Menu;
import models.lists.DishCardList;

public class OrderBehaviour extends OneShotBehaviour {
    private final int[] dishes;
    private final String visitorName;
    public OrderBehaviour(int[] dishes, String visitorName) {
        this.dishes = dishes;
        this.visitorName = visitorName;
    }

    public void action() {
        double time = 0;
        for (int dish : dishes) {
            int cardID = getCardID(dish);
            time += getTime(cardID);
        }
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.addReceiver(new AID(visitorName, AID.ISLOCALNAME));
        msg.setContent(time + "");
        myAgent.send(msg);
        try {
            Thread.sleep((int) (time * 100000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        msg = new ACLMessage(ACLMessage.INFORM);
        msg.addReceiver(new AID(visitorName, AID.ISLOCALNAME));
        msg.setContent("ready");
        myAgent.send(msg);
        myAgent.doDelete();
    }

    private int getCardID(int dish) {
        var menuDishes = Menu.getInstance().menu_dishes;
        for (var menuDish : menuDishes) {
            if (menuDish.menu_dish_id() == dish) {
                return menuDish.menu_dish_card();
            }
        }
        return -1;
    }

    private double getTime(int cardID) {
        var dishCards = DishCardList.getInstance().dish_cards;
        for (var dishCard : dishCards) {
            if (dishCard.card_id() == cardID) {
                return dishCard.card_time();
            }
        }
        return -1;
    }
}
