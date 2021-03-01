package dev.zhen.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.gson.Gson;
import dev.zhen.customException.ExpenseNotFoundException;
import dev.zhen.entities.Expense;
import dev.zhen.services.ExpenseService;
import dev.zhen.utils.JwtUtil;
import io.javalin.http.Handler;
import org.apache.log4j.Logger;

import java.util.List;

public class ExpenseController {

    private static Logger logger = Logger.getLogger(ExpenseController.class.getName());
    private static Gson gson = new Gson();
    private ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    public Handler createExpenseHandler = ctx -> {
        String body = ctx.body();
        Expense expense = gson.fromJson(body, Expense.class);
        if (expense != null) {
            expense = expenseService.createExpense(expense.getEmployeeId(), expense);
            if (expense == null) {
                ctx.result("Failed to create expense");
                ctx.status(400);
            } else {
                ctx.result(gson.toJson(expense));
                ctx.status(201);
            }
        } else {
            ctx.result("Body can't be empty");
            ctx.status(400);
        }
    };

    public Handler getAllExpenseHandler = ctx -> {
        String employeeId = ctx.queryParam("employeeId", "NONE");
        List<Expense> expenseList;
        if (employeeId.equals("NONE")) {
            expenseList = expenseService.getAllExpense();
        }  else {
            expenseList = expenseService.getAllExpenseByEmployeeId(Integer.parseInt(employeeId));
        }
        if (expenseList != null && expenseList.size() > 0) {
            ctx.result(gson.toJson(expenseList));
            ctx.status(200);
        } else {
            ctx.result("Failed to get all expense");
            ctx.status(404);
        }
    };

    public Handler getExpenseByIdHandler = ctx -> {
        int expenseId = Integer.parseInt(ctx.pathParam("eid"));
        Expense expense = expenseService.getExpenseById(expenseId);
        if (expense != null) {
            ctx.result(gson.toJson(expense));
            ctx.status(200);
        } else {
            ctx.result("Failed to get expense by id: expense not found");
            ctx.status(404);
        }
    };
    public Handler updateExpenseByIdHandler = ctx -> {
        String jwt = ctx.header("Authorization");
        DecodedJWT decodedJWT = JwtUtil.verifyToken(jwt);
        if (decodedJWT != null) {
            String role = decodedJWT.getClaim("role").asString();
            if (role.toLowerCase().equals("manager")) {
                int expenseId = Integer.parseInt(ctx.pathParam("eid"));
                String body = ctx.body();
                Expense updateExpense = gson.fromJson(body, Expense.class);
                if (updateExpense != null) {
                    try {
                        Expense expense = expenseService.updateExpenseById(expenseId, updateExpense);
                        if (expense != null) {
                            ctx.result(gson.toJson(expense));
                            ctx.status(200);
                        } else {
                            ctx.result("Failed to update expense by id");
                            ctx.status(400);
                        }
                    } catch (ExpenseNotFoundException expenseNotFoundException) {
                        expenseNotFoundException.printStackTrace();
                        ctx.result("Failed to update expense by id: expense not found");
                        ctx.status(404);
                    }
                } else {
                    ctx.result("Failed to update expense by id");
                    ctx.status(400);
                }
            } else {
                ctx.result("Employee is not authorized to this resource");
                ctx.status(403);
            }

        } else {
            ctx.result("Unauthorized token");
            ctx.status(401);
        }
    };
    public Handler deleteExpenseByIdHandler = ctx -> {
        int expenseId = Integer.parseInt(ctx.pathParam("eid"));
        try {
            boolean isDeleted = expenseService.deleteExpenseById(expenseId);
            if (isDeleted) {
                ctx.result("Expense deleted");
                ctx.status(200);
            } else {
                ctx.result("Failed to delete expense by id");
                ctx.status(400);
            }
        } catch (ExpenseNotFoundException expenseNotFoundException) {
            expenseNotFoundException.printStackTrace();
            ctx.result("Failed to delete expense by id: expense not found");
            ctx.status(404);
        }
    };
}
