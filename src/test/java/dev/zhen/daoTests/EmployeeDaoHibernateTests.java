package dev.zhen.daoTests;

import dev.zhen.daos.EmployeeDAO;
import dev.zhen.daos.EmployeeDaoHibernate;
import dev.zhen.entities.Employee;
import org.junit.jupiter.api.Test;

public class EmployeeDaoHibernateTests {

    EmployeeDAO employeeDAO = new EmployeeDaoHibernate();

    @Test
    void get_employee_by_username() {
        Employee employee = employeeDAO.getEmployeeByUsername("tifa","1");
        System.out.println(employee);
    }

    @Test
    void get_employee_by_id() {
        Employee employee = employeeDAO.getEmployeeById(1);
        System.out.println(employee);
    }

}
