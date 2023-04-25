package com.box.delivery.app.dto;

public class Order {
    private final int id;

    private final String orderName;
    private final String destinationCode;

    public Order(int id, String destinationCode, String orderName) {
        this.id = id;
        this.destinationCode = destinationCode;
        this.orderName = orderName;
    }

    public int getId() {
        return id;
    }

    public String getDestinationCode() {
        return destinationCode;
    }

    public String getOrderName() {
        return orderName;
    }
}
