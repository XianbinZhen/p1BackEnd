package dev.zhen.app;

import dev.zhen.controller.ExpenseController;
import dev.zhen.controller.ExpenseJoinEmployeeController;
import dev.zhen.controller.LoginController;
import dev.zhen.daos.EmployeeDaoPostgres;
import dev.zhen.daos.ExpenseDaoHibernate;
import dev.zhen.daos.ExpenseJoinEmployeeDaoHibernate;
import dev.zhen.services.EmployeeServiceImpl;
import dev.zhen.services.ExpenseJoinEmployeeServiceImpl;
import dev.zhen.services.ExpenseServiceImpl;
import io.javalin.Javalin;

public class App {
    public static void main(String[] args) {
        Javalin app = Javalin.create(
                javalinConfig -> {
                    javalinConfig.enableCorsForAllOrigins();
                }
        );

        ExpenseController expenseController = new ExpenseController(new ExpenseServiceImpl(new ExpenseDaoHibernate()));
        LoginController loginController = new LoginController(new EmployeeServiceImpl(new EmployeeDaoPostgres()));
        ExpenseJoinEmployeeController expenseJoinEmployeeController = new ExpenseJoinEmployeeController(new ExpenseJoinEmployeeServiceImpl(new ExpenseJoinEmployeeDaoHibernate()));
        app.post("/expense", expenseController.createExpenseHandler);
        app.get("/expense", expenseController.getAllExpenseHandler);
        app.get("/expense/:eid", expenseController.getExpenseByIdHandler);
        app.post("/expense/:eid", expenseController.updateExpenseByIdHandler);
        app.delete("/expense/:eid", expenseController.deleteExpenseByIdHandler);

        app.get("/expenseJoinEmployee", expenseJoinEmployeeController.getExpenseJoinEmployeeHandler);

        app.post("/users/login", loginController.loginHandler);

        app.start();
    }
}
