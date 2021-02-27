package dev.zhen.app;

import dev.zhen.controller.ExpenseController;
import dev.zhen.daos.ExpenseDaoPostgres;
import dev.zhen.services.ExpenseServiceImpl;
import io.javalin.Javalin;

public class App {
    public static void main(String[] args) {
        Javalin app = Javalin.create(
                javalinConfig -> {
                    javalinConfig.enableCorsForAllOrigins();
                }
        );

        ExpenseController expenseController = new ExpenseController(new ExpenseServiceImpl(new ExpenseDaoPostgres()));

        app.post("/expense", expenseController.createExpenseHandler);
        app.get("/expense", expenseController.getAllExpenseHandler);
        app.get("/expense/:eid", expenseController.getExpenseByIdHandler);
        app.post("/expense/:eid", expenseController.updateExpenseByIdHandler);
        app.delete("/expense/:eid", expenseController.deleteExpenseByIdHandler);

        app.start();

    }
}
