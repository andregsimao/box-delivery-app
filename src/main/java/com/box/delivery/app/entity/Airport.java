package com.box.delivery.app.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "airport")
public class Airport {
    @Id
    @Column(name = "code")
    private String code;

    public Airport() {}

    public Airport(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "Airport(code = \"" + code + "\")";
    }
}
