package models;

public record Visitor(String vis_name, String vis_ord_started, String vis_ord_ended, int vis_ord_total, Order[] vis_ord_dishes) {
}
