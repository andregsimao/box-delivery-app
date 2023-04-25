package com.box.delivery.app.entity;

import java.util.Set;
import jakarta.persistence.*;

@Entity
@Table(name = "airport")
public class Airport {
    @Id
    @Column(name = "code")
    private String code;

    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "departureAirport")
    private Set<Flight> departureFlights;

    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "arrivalAirport")
    private Set<Flight> arrivalFlights;

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
