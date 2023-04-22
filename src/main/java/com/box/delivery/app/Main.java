package com.box.delivery.app;

import com.box.delivery.app.config.HibernateUtil;
import com.box.delivery.app.entity.Airport;
import com.box.delivery.app.entity.Flight;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Airport departure = new Airport("YYC", "Calgary");
        Airport arrival = new Airport("GIG", "Galeao");
        Flight flight = new Flight(1, 2, departure, arrival);
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student objects
            session.persist(flight);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List< Airport > airports = session.createQuery("from Airport", Airport.class).list();
            airports.forEach(a -> System.out.println(a.getCode()));
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}

