package dev.zhen.services;
import dev.zhen.daos.ExpenseJoinEmployeeDAO;
import dev.zhen.entities.ExpenseJoinEmployee;

import java.util.List;

public class ExpenseJoinEmployeeServiceImpl implements ExpenseJoinEmployeeService{

    private ExpenseJoinEmployeeDAO expenseJoinEmployeeDAO;

    public ExpenseJoinEmployeeServiceImpl(ExpenseJoinEmployeeDAO expenseJoinEmployeeDAO) {
        this.expenseJoinEmployeeDAO = expenseJoinEmployeeDAO;
    }

    @Override
    public List<ExpenseJoinEmployee> getAllExpenseJoinEmployee() {
        return expenseJoinEmployeeDAO.getAllExpenseJoinEmployee();
    }
}
