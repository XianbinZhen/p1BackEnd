package dev.zhen.serviceTests;

import dev.zhen.customException.ExpenseNotFoundException;
import dev.zhen.daos.ExpenseDaoPostgres;
import dev.zhen.entities.Expense;
import dev.zhen.enums.Status;
import dev.zhen.services.ExpenseService;
import dev.zhen.services.ExpenseServiceImpl;
import org.junit.jupiter.api.*;

import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ExpenseServiceTests {


    ExpenseService expenseService = new ExpenseServiceImpl(new ExpenseDaoPostgres());

    @Test
    @Order(0)
    void create_expense_test() {
        Expense expense1 = new Expense(0, 0, 123, "Bus", Status.APPROVED,0,0, "");
        Expense expense2 = new Expense(0, 0, 1230, "Plane", Status.APPROVED,0,0, "");
        Expense createdExpense1 = expenseService.createExpense(1, expense1);
        Expense createdExpense2 = expenseService.createExpense(1, expense2);
        Assertions.assertNotNull(createdExpense1);
        Assertions.assertEquals(createdExpense1.getExpenseId() + 1, createdExpense2.getExpenseId());
    }

    @Test
    @Order(1)
    void get_all_expense_test() {
        List<Expense> allExpense = expenseService.getAllExpense();
        int before = allExpense.size();
        Expense expense1 = new Expense(0, 0, 423, "Ship", Status.APPROVED,0,0, "");
        expenseService.createExpense(1, expense1);
        allExpense = expenseService.getAllExpense();
        Assertions.assertNotNull(allExpense);
        Assertions.assertNotEquals(0, allExpense.size());
        Assertions.assertEquals(before + 1, allExpense.size());
    }

    @Test
    @Order(3)
    void get_all_expense_by_employee_id() {
        List<Expense> allExpense = expenseService.getAllExpenseByEmployeeId(1);
        int before = allExpense.size();
        Expense expense1 = new Expense(0, 0, 423, "Ship", Status.APPROVED,0,0, "");
        expenseService.createExpense(1, expense1);
        allExpense = expenseService.getAllExpenseByEmployeeId(1);
        Assertions.assertNotNull(allExpense);
        Assertions.assertNotEquals(0, allExpense.size());
        Assertions.assertEquals(before + 1, allExpense.size());
    }

    @Test
    @Order(4)
    void get_expense_by_id() {
        Expense expense = new Expense(0, 0, 230, "train", Status.APPROVED,0,0, "");
        expense = expenseService.createExpense(1, expense);
        Expense returnExpense = expenseService.getExpenseById(expense.getExpenseId());
        Assertions.assertEquals(returnExpense.getExpenseId(), expense.getExpenseId());
    }

    @Test
    @Order(5)
    void update_expense_by_id() {
        Expense expense = new Expense(0, 0, 423, "Book", Status.DENIED,0,0, "");
        expense = expenseService.createExpense(1, expense);
        Expense updateExpense = new Expense(expense.getExpenseId(), 2, 323, "Book", Status.APPROVED,1,1, "");
        try {
            expense = expenseService.updateExpenseById(updateExpense);
            Assertions.assertEquals(expense.getAmount(), 323);
            Assertions.assertEquals(expense.getDateSubmitted(), 1);
        } catch (ExpenseNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    @Order(6)
    void delete_expense_by_id() {
        Expense expense = new Expense(0, 0, 423, "Book", Status.DENIED,0,0, "");
        expense = expenseService.createExpense(1, expense);
        int before = expenseService.getAllExpense().size();
        try {
            expenseService.deleteExpenseById(expense.getExpenseId());
            Assertions.assertEquals(before, expenseService.getAllExpense().size() + 1);
        } catch (ExpenseNotFoundException e) {
            e.printStackTrace();
        }
    }

}
