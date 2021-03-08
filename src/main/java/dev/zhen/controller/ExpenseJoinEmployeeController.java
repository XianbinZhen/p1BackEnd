package dev.zhen.controller;

import com.google.gson.Gson;
import dev.zhen.entities.ExpenseJoinEmployee;
import dev.zhen.services.ExpenseJoinEmployeeService;
import io.javalin.http.Handler;
import org.apache.log4j.Logger;

import java.util.List;

public class ExpenseJoinEmployeeController {
    private static Logger logger = Logger.getLogger(ExpenseController.class.getName());
    private static Gson gson = new Gson();
    private ExpenseJoinEmployeeService expenseJoinEmployeeService;

    public ExpenseJoinEmployeeController(ExpenseJoinEmployeeService expenseJoinEmployeeService) {
        this.expenseJoinEmployeeService = expenseJoinEmployeeService;
    }

    public Handler getExpenseJoinEmployeeHandler = ctx -> {
       List<ExpenseJoinEmployee> expenseJoinEmployeeList = expenseJoinEmployeeService.getAllExpenseJoinEmployee();
       if (expenseJoinEmployeeList.size() > 0) {
           ctx.result(gson.toJson(expenseJoinEmployeeList));
           ctx.status(200);
       } else {
           ctx.result("Failed to get expense join employee");
           ctx.status(404);
       }
    };

}
