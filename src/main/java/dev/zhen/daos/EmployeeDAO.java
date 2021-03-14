package dev.zhen.daos;

import dev.zhen.entities.Employee;

import java.util.List;

public interface EmployeeDAO {
    public Employee getEmployeeByUsername(String username, String password);
    public Employee getEmployeeById(int employeeId);
    public List<Employee> getAllEmployee();

}
