package dev.zhen.utilTests;

import dev.zhen.utils.HibernateUtil;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

public class HibernateUtilTest {

    @Test
    void create_session_factory() {
        SessionFactory sessionFactory = HibernateUtil.createSessionFactory();
        System.out.println(sessionFactory);
    }

}
