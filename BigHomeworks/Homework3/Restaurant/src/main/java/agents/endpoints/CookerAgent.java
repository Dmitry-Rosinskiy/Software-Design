package agents.endpoints;

import agents.BasicAgent;
import jade.core.behaviours.TickerBehaviour;

public class CookerAgent extends BasicAgent {
    // представляет конкретного человека – повара ресторана, взаимодействует с ним через кухонный сенсорный терминал, управляет его работой (назначает ему выполнение определенной операции, отменяет / приостанавливает (опционально) ранее назначенную ему операцию, возобновляет ранее приостановленную им операцию), принимает от него события, связанные с выполнением им операций («приступил к выполнению операции», «выполнил операцию», «отменил выполнение операции» (испорчен продукт в процессе приготовления)).

    public void setup() {
        super.setup();
        addBehaviour(new TickerBehaviour(this, 10000) {
            @Override
            protected void onTick() {
            }
        });
    }
}
