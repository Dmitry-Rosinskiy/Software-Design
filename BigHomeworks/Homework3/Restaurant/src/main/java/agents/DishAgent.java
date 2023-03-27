package agents;

public class DishAgent extends BasicAgent {
    // содержит списки созданных управляющим агентом
    // агентов процесса, операций и агентов продуктов
    // для приготовления конкретного блюда / напитка из
    // заказа посетителя. Уничтожается, когда данное блюдо
    // / напиток приготовлено, а заказ выполнен.

    public void setup() {
        super.setup();
        String name = (String) startupArgs[0];
        System.out.println(name);
    }
}
