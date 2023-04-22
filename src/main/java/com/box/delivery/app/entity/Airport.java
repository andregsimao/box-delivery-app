package com.box.delivery.app.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "airport")
public class Airport {
    @Id
    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    public Airport() {}

    public Airport(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
