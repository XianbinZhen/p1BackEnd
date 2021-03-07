package dev.zhen.daos;

import dev.zhen.entities.Employee;

public interface EmployeeDAO {
    public Employee getEmployeeByUsername(String username, String password);
    public Employee getEmployeeById(int employeeId);
}
