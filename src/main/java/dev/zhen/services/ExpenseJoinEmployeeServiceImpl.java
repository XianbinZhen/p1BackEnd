package dev.zhen.services;

import dev.zhen.daos.EmployeeDAO;
import dev.zhen.daos.ExpenseDAO;
import dev.zhen.entities.Employee;
import dev.zhen.entities.Expense;
import dev.zhen.entities.ExpenseJoinEmployee;

import java.util.LinkedList;
import java.util.List;

public class ExpenseJoinEmployeeServiceImpl implements ExpenseJoinEmployeeService{

    private EmployeeDAO employeeDAO;
    private ExpenseDAO expenseDAO;

    public ExpenseJoinEmployeeServiceImpl(ExpenseDAO expenseDAO, EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
        this.expenseDAO = expenseDAO;
    }

    @Override
    public List<ExpenseJoinEmployee> getAllExpenseJoinEmployee() {
        List<Expense> expenseList = expenseDAO.getAllExpense();
        List<ExpenseJoinEmployee> expenseJoinEmployeeList = new LinkedList<>();
        for (Expense expense : expenseList) {
            Employee employee = employeeDAO.getEmployeeById(expense.getEmployeeId());
            expenseJoinEmployeeList.add(new ExpenseJoinEmployee(expense, employee));
        }
        if (expenseJoinEmployeeList.size() > 0)
            return expenseJoinEmployeeList;
        else
            return null;
    }
}
