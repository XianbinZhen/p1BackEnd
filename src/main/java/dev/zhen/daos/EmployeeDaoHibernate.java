package dev.zhen.daos;

import dev.zhen.entities.Employee;
import dev.zhen.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class EmployeeDaoHibernate implements EmployeeDAO{
    @Override
    public Employee getEmployeeByUsername(String username, String password) {
        SessionFactory sessionFactory = HibernateUtil.createSessionFactory();
        Session session = sessionFactory.openSession();
        String hql = "from Employee e where e.username = :username and e.password = :password";
        Query query = session.createQuery(hql);
        query.setParameter("username", username);
        query.setParameter("password", password);
        List<Employee> employeeList = query.list();
        session.close();
        if (employeeList.size() > 0) {
            Employee employee = employeeList.get(0);
            return employee;
        } else {
            return null;
        }
    }

    @Override
    public Employee getEmployeeById(int employeeId) {
        SessionFactory sessionFactory = HibernateUtil.createSessionFactory();
        Session session = sessionFactory.openSession();
        String hql = "from Employee e where e.employeeId = :employeeId";
        Query query = session.createQuery(hql);
        query.setParameter("employeeId", employeeId);
        List<Employee> employeeList = query.list();
        session.close();
        if (employeeList.size() > 0) {
            Employee employee = employeeList.get(0);
            return employee;
        } else {
            return null;
        }
    }
}
