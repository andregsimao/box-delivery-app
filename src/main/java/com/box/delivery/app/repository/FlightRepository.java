package com.box.delivery.app.repository;

import com.box.delivery.app.config.HibernateUtil;
import com.box.delivery.app.entity.Flight;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class FlightRepository {

    private static FlightRepository instance;

    private FlightRepository() {}

    public static FlightRepository getInstance() {
        if (instance == null) {
            instance = new FlightRepository();
        }
        return instance;
    }

    public void persist(Flight flight) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(flight);
        transaction.commit();
        session.close();
    }

    public List<Flight> getFlights() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Flight> flights = session.createQuery("from Flight", Flight.class).list();
        session.close();
        return flights;
    }

    public Long getMaxFlightId() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Long maxId = session.createQuery("select max(id) from Flight", Long.class).getSingleResultOrNull();
        session.close();
        return maxId;
    }
}
