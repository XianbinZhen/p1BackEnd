package dev.zhen.services;

import dev.zhen.daos.EmployeeDAO;
import dev.zhen.entities.Employee;

public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeDAO employeeDAO;

    public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }



    @Override
    public Employee getEmployeeByUsername(String username, String password) {
        return employeeDAO.getEmployeeByUsername(username, password);
    }
}
