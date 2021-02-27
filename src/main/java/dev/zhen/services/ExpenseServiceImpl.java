package dev.zhen.services;

import dev.zhen.customException.ExpenseNotFoundException;
import dev.zhen.daos.ExpenseDAO;
import dev.zhen.entities.Expense;

import java.util.List;


public class ExpenseServiceImpl implements ExpenseService{

    private ExpenseDAO expenseDAO;

    public ExpenseServiceImpl(ExpenseDAO expenseDAO) {
        this.expenseDAO = expenseDAO;
    }


    @Override
    public Expense createExpense(int employeeId, Expense expense) {
        return expenseDAO.createExpense(employeeId, expense);
    }

    @Override
    public List<Expense> getAllExpense() {
        return expenseDAO.getAllExpense();
    }

    @Override
    public List<Expense> getAllExpenseByEmployeeId(int employeeId) {
        return expenseDAO.getAllExpenseByEmployeeId(employeeId);
    }

    @Override
    public Expense getExpenseById(int id) {
        return expenseDAO.getExpenseById(id);
    }

    @Override
    public Expense updateExpenseById(int id, Expense expense) throws ExpenseNotFoundException {
        return expenseDAO.updateExpenseById(id, expense);
    }

    @Override
    public boolean deleteExpenseById(int id) throws ExpenseNotFoundException {
        return expenseDAO.deleteExpenseById(id);
    }
}
