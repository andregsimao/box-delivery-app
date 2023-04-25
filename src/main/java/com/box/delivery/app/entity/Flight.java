package com.box.delivery.app.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "flight", indexes = @Index(columnList = "flight_day"))
public class Flight {

    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "flight_day")
    private int flightDay;

    @ManyToOne(fetch = FetchType.EAGER, optional = false, cascade=CascadeType.ALL)
    @JoinColumn(name = "airplane_id", referencedColumnName = "id")
    private Airplane airplane;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "departure", referencedColumnName = "code")
    private Airport departureAirport;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "arrival", referencedColumnName = "code")
    private Airport arrivalAirport;

    public Flight() {}

    public Flight(long id, int flightDay, Airport departureAirport, Airport arrivalAirport, Airplane airplane) {
        this.id = id;
        this.flightDay = flightDay;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.airplane = airplane;
    }

    public long getId() {
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

    public Airplane getAirplane() {
        return airplane;
    }

    public int getCapacity() {
        return airplane.getCapacity();
    }

    @Override
    public String toString() {
        return "Flight(id = " + id +
            ", flightDay = " + flightDay +
            ", departure = " + departureAirport +
            ", arrival = " + arrivalAirport +
            ", airplane = " + airplane +
            ")";
    }
}
