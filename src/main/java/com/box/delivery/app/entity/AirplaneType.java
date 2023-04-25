package com.box.delivery.app.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "airplane_type")
public class AirplaneType {
    @Id
    @Column(name = "id")
    int id;

    @Column(name = "capacity")
    int capacity;

    public AirplaneType() {}

    public AirplaneType(int id, int capacity) {
        this.id = id;
        this.capacity = capacity;
    }

    public int getId() {
        return id;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return "AirplaneType(id = " + id +
            ", capacity = " + capacity +
            ")";
    }
}

