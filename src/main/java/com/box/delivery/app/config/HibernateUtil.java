package com.box.delivery.app.config;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HibernateUtil {
    private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;

    private static final Logger logger = LoggerFactory.getLogger(HibernateUtil.class);

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                logger.info("creating hibernate sessionFactory");

                registry = new StandardServiceRegistryBuilder().configure().build();

                MetadataSources sources = new MetadataSources(registry);

                Metadata metadata = sources.getMetadataBuilder().build();

                sessionFactory = metadata.getSessionFactoryBuilder().build();

                logger.info("hibernate sessionFactory created");
            } catch (Exception e) {
                logger.error("exception creating hibernate sessionFactory");
                e.printStackTrace();
                if (registry != null) {
                    StandardServiceRegistryBuilder.destroy(registry);
                    logger.info("registry destroyed due to exception creating hibernate sessionFactory");
                }
            }
        }
        return sessionFactory;
    }

    public static void init() {
        getSessionFactory();
    }

    public static void destroy() {
        if (registry != null) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
