package dev.zhen.controller;

import com.google.gson.Gson;
import dev.zhen.entities.Employee;
import dev.zhen.services.EmployeeService;
import dev.zhen.utils.JwtUtil;
import io.javalin.http.Handler;
import org.apache.log4j.Logger;

public class LoginController {

    private EmployeeService employeeService;

    Logger logger = Logger.getLogger(LoginController.class.getName());

    public LoginController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public Handler loginHandler = ctx -> {
        String body = ctx.body();
        Gson gson = new Gson();
        Employee employee = gson.fromJson(body, Employee.class);
        if (employee != null && employee.getUsername() != null && employee.getPassword() != null) {
            employee = employeeService.getEmployeeByUsername(employee.getUsername(), employee.getPassword());
            if(employee != null) {
                String role = employee.isManager() ? "manager" : "employee";
//                String employeeName = employee.getFirstName() + " " + employee.getLastName();
                String jwt = JwtUtil.generate(role, employee.getFirstName(), employee.getLastName(), employee.getEmployeeId());
//            ctx.cookie("jwt", jwt, 3600 * 24);
                ctx.result(jwt);
                ctx.status(201);
                logger.info("username: " + employee.getUsername() + " login.");
            } else {
                ctx.result("Incorrect username or password");
                ctx.status(400);
                logger.error("Incorrect username or password! Body: " + body);
            }
        } else {
            ctx.result("Failed to generate jwt");
            ctx.status(400);
            logger.error("Failed to generate jwt! Body: " + body);
        }
    };

}
