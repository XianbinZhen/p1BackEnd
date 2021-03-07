package dev.zhen.hibernateTests;

import dev.zhen.entities.Employee;
import dev.zhen.entities.Expense;
import dev.zhen.enums.Status;
import dev.zhen.utils.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.Test;

import java.util.List;


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

    @Test
    void get_expense_from_employee() {
        SessionFactory sessionFactory = HibernateUtil.createSessionFactory();
        Session session = sessionFactory.openSession();
        String hql = "from Employee e where e.employeeId = :employeeId";
        Query query = session.createQuery(hql);
        query.setParameter("employeeId", 1);
        List<Employee> employeeList = query.list();
        Employee employee = employeeList.get(0);
        List<Expense> expenseList = employee.getExpenseList();
        System.out.println(expenseList);
        session.close();
    }


}
