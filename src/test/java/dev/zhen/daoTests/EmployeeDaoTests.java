package dev.zhen.daoTests;

import dev.zhen.daos.EmployeeDAO;
import dev.zhen.daos.EmployeeDaoPostgres;
import dev.zhen.entities.Employee;
import org.junit.jupiter.api.Test;

public class EmployeeDaoTests {

    EmployeeDAO employeeDAO = new EmployeeDaoPostgres();

    @Test
    void get_employee_by_username() {
        Employee employee = employeeDAO.getEmployeeByUsername("cloud","1");
        System.out.println(employee);
    }

    @Test
    void get_employee_by_id() {
        Employee employee = employeeDAO.getEmployeeById(1);
        System.out.println(employee);

    }
}
