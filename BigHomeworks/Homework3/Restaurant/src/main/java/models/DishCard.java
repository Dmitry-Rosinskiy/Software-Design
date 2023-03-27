package models;

public record DishCard(int card_id, String dish_name, String card_descr, double card_time, int equip_type, Operation[] operations) {
}
