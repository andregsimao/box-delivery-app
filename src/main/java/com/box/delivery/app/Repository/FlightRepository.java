package com.box.delivery.app.Repository;

import com.box.delivery.app.config.HibernateUtil;
import com.box.delivery.app.entity.Flight;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FlightRepository {

    private static final Logger logger = LoggerFactory.getLogger(FlightRepository.class);

    private static FlightRepository instance;

    private FlightRepository() {}

    public static FlightRepository getInstance() {
        if (instance == null) {
            instance = new FlightRepository();
        }
        return instance;
    }

    public void persistFlight(Flight flight) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(flight);
        transaction.commit();
        session.close();
        logger.info("flight " + flight + " persisted database");
    }

    public List<Flight> getFlights() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Flight> flights = session.createQuery("from Flight", Flight.class).list();
        session.close();
        return flights;
    }
}
