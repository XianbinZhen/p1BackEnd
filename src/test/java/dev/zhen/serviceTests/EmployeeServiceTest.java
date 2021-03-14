package dev.zhen.serviceTests;

import dev.zhen.daos.EmployeeDAO;
import dev.zhen.entities.Employee;
import dev.zhen.services.EmployeeService;
import dev.zhen.services.EmployeeServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.LinkedList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    EmployeeDAO employeeDAO = null;
    EmployeeService employeeService = null;

    @BeforeEach
    void setUp() {
        Employee employee1 = new Employee(1,"joker", "1", "Ren", "Amamiya", true);
        Employee employee2 = new Employee(2, "violet", "1", "Kasumi", "Yoshizawa", false);
        Employee employee3 = new Employee(3, "panther", "1", "Anne", "Takamaki", false);
        List<Employee> employeeList = new LinkedList<>();
        employeeList.add(employee1);
        employeeList.add(employee2);
        employeeList.add(employee3);
        Mockito.when(employeeDAO.getAllEmployee()).thenReturn(employeeList);
        this.employeeService = new EmployeeServiceImpl(employeeDAO);


    }

    @Test
    void get_employee_by_username() {
        Employee employee = employeeService.getEmployeeByUsernameOnly("joker");
        Assertions.assertTrue(employee.getUsername().equals("joker"));
        System.out.println(employee);
        Employee employee2 = employeeService.getEmployeeByUsernameOnly("panther");
        Assertions.assertTrue(employee2.getUsername().equals("panther"));
        System.out.println(employee2);
    }
}
