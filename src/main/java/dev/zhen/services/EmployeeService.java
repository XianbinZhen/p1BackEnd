package dev.zhen.services;

import dev.zhen.entities.Employee;

public interface EmployeeService {
    public Employee getEmployeeByUsername(String username, String password);
}
