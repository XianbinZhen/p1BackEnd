package dev.zhen.daos;

import dev.zhen.customException.ExpenseNotFoundException;
import dev.zhen.entities.Expense;

import java.util.List;

public interface ExpenseDAO {

    Expense createExpense(int employeeId, Expense expense);
    List<Expense> getAllExpense();
    List<Expense> getAllExpenseByEmployeeId(int employeeId);
    Expense getExpenseById(int id);
    Expense updateExpenseById(int id, Expense expense) throws ExpenseNotFoundException;
    boolean deleteExpenseById(int id) throws ExpenseNotFoundException;

}
