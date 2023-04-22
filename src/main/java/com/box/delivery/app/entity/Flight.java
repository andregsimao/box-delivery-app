package com.box.delivery.app.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "flight", indexes = @Index(columnList = "flight_day"))
public class Flight {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "flight_day")
    private int flightDay;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade= {CascadeType.ALL,CascadeType.PERSIST})
    @JoinColumn(name = "departure", referencedColumnName = "code")
    private Airport departureAirport;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade= {CascadeType.ALL,CascadeType.PERSIST})
    @JoinColumn(name = "arrival", referencedColumnName = "code")
    private Airport arrivalAirport;

    public Flight() {}

    public Flight(int id, int flightDay, Airport departureAirport, Airport arrivalAirport) {
        this.id = id;
        this.flightDay = flightDay;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
    }

    public int getId() {
        return id;
    }

    public int getFlightDay() {
        return flightDay;
    }

    public Airport getDepartureAirport() {
        return departureAirport;
    }

    public Airport getArrivalAirport() {
        return arrivalAirport;
    }
}
