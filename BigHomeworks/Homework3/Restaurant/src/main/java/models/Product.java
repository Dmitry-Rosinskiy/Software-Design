package models;

public record Product(int prod_item_id, int prod_item_type, String prod_item_name, String prod_item_company, String prod_item_unit, double prod_item_quantity, int prod_item_cost, String prod_item_delivered, String prod_item_valid_until) {
}
