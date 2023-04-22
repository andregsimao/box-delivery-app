package com.box.delivery.app.repository;

import com.box.delivery.app.config.HibernateUtil;
import com.box.delivery.app.entity.Airport;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AirportRepository {
    private static AirportRepository instance;

    private AirportRepository() {}

    public static AirportRepository getInstance() {
        if (instance == null) {
            instance = new AirportRepository();
        }
        return instance;
    }

    public void merge(Airport airport) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(airport);
        transaction.commit();
        session.close();
    }
}
