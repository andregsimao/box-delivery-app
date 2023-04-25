package com.box.delivery.app.repository;

import com.box.delivery.app.config.HibernateUtil;
import com.box.delivery.app.entity.Airplane;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AirplaneRepository {
    private static AirplaneRepository instance;

    private AirplaneRepository() {}

    public static AirplaneRepository getInstance() {
        if (instance == null) {
            instance = new AirplaneRepository();
        }
        return instance;
    }

    public void merge(Airplane airplane) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(airplane);
        transaction.commit();
        session.close();
    }
}
