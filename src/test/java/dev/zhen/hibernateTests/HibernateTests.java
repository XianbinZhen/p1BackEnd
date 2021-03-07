package dev.zhen.hibernateTests;

import dev.zhen.entities.Expense;
import dev.zhen.enums.Status;
import dev.zhen.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

public class HibernateTests {

    @Test
    void create_expense() {
        SessionFactory sessionFactory = HibernateUtil.createSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Expense expense = new Expense(0, 1, 923, "cat", Status.APPROVED,99,999, "");
        session.save(expense);
        session.getTransaction().commit();
        session.close();
    }



}
