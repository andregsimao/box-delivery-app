package com.box.delivery.app.repository;

import com.box.delivery.app.config.HibernateUtil;
import com.box.delivery.app.entity.AirplaneType;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AirplaneTypeRepository {

    private static AirplaneTypeRepository instance;

    private AirplaneTypeRepository() {}

    public static AirplaneTypeRepository getInstance() {
        if (instance == null) {
            instance = new AirplaneTypeRepository();
        }
        return instance;
    }

    public void merge(AirplaneType airplaneType) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(airplaneType);
        transaction.commit();
        session.close();
    }
}