package config;

import com.box.delivery.app.config.HibernateUtil;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HibernateUtilTest {

    @Test
    public void Should_ReturnSameInstance_When_GettingSessionFactory() {
        SessionFactory sessionFactory1 = HibernateUtil.getSessionFactory();
        SessionFactory sessionFactory2 = HibernateUtil.getSessionFactory();
        assertEquals(sessionFactory1, sessionFactory2);
    }
}
