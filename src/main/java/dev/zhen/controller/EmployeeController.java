package dev.zhen.controller;

import com.google.gson.Gson;
import dev.zhen.entities.Employee;
import dev.zhen.services.EmployeeService;
import dev.zhen.services.ExpenseService;
import io.javalin.http.Handler;
import org.apache.log4j.Logger;

public class EmployeeController {
    private static Logger logger = Logger.getLogger(ExpenseController.class.getName());
    private static Gson gson = new Gson();
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public Handler getEmployeeByUsernameHandler = ctx -> {
        String username = ctx.queryParam("username", "NONE");
        String password = ctx.queryParam("password", "NONE");
        if (username.equals("NONE") || password.equals("NONE")) {
            ctx.result("Failed to get employee");
            ctx.status(404);
        } else {
            Employee employee = employeeService.getEmployeeByUsername(username, password);
            ctx.status(200);
            ctx.result(gson.toJson(employee));
        }
    };
}
