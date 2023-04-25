package com.box.delivery.app.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "airplane")
public class Airplane {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false, cascade=CascadeType.ALL )
    @JoinColumn(name = "airplane_type_id", referencedColumnName = "id")
    private AirplaneType airplaneType;

    public Airplane() {}

    public Airplane(AirplaneType airplaneType) {
        this.airplaneType = airplaneType;
    }

    public int getId() {
        return id;
    }

    public AirplaneType getAirplaneType() {
        return airplaneType;
    }

    public int getCapacity() {
        return airplaneType.getCapacity();
    }

    @Override
    public String toString() {
        return "Airplane(id = " + id +
            ", airplaneType = " + airplaneType +
            ")";
    }
}
