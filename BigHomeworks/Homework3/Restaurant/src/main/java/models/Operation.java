package models;

public record Operation(int oper_type, double oper_time, int oper_async_point, OperationProducts[] oper_products) {
}
