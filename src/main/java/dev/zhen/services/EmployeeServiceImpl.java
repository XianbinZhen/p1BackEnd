package dev.zhen.services;

import dev.zhen.daos.EmployeeDAO;
import dev.zhen.entities.Employee;

import java.util.List;


public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeDAO employeeDAO;

    public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    public Employee getEmployeeByUsername(String username, String password) {
        return employeeDAO.getEmployeeByUsername(username, password);
    }

    @Override
    public Employee getEmployeeByUsernameOnly(String username) {
        List<Employee> employeeList = employeeDAO.getAllEmployee();
        if(employeeList.size() > 0) {
            for (Employee e : employeeList) {
                if (e.getUsername().toLowerCase().equals(username.toLowerCase())) {
                    return e;
                }
            }
        }
        return null;
    }
}
