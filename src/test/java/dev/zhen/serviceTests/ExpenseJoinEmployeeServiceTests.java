package dev.zhen.serviceTests;

import dev.zhen.daos.EmployeeDaoPostgres;
import dev.zhen.daos.ExpenseDaoPostgres;
import dev.zhen.entities.ExpenseJoinEmployee;
import dev.zhen.services.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ExpenseJoinEmployeeServiceTests {

    ExpenseJoinEmployeeService expenseJoinEmployeeServicee = new ExpenseJoinEmployeeServiceImpl(
            new ExpenseDaoPostgres(), new EmployeeDaoPostgres());

    @Test
    void get_all_expense_join_employee_test() {
        List<ExpenseJoinEmployee> expenseJoinEmployeeList = expenseJoinEmployeeServicee.getAllExpenseJoinEmployee();
        Assertions.assertNotNull(expenseJoinEmployeeList);
        for(ExpenseJoinEmployee e : expenseJoinEmployeeList) {
            System.out.println(e.getExpense());
            System.out.println(e.getEmployee());
        }
    }
}
