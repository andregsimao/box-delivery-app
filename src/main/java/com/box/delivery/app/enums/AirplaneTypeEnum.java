package com.box.delivery.app.enums;

public enum AirplaneTypeEnum {
    GENERIC(1);

    final int id;

    AirplaneTypeEnum(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
