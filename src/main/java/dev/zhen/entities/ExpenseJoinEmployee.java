package dev.zhen.entities;

import dev.zhen.enums.Status;

public class ExpenseJoinEmployee {
    private Expense expense;
    private Employee employee;

    public ExpenseJoinEmployee() {
    }

    public ExpenseJoinEmployee(Expense expense, Employee employee) {
        this.expense = expense;
        this.employee = employee;
    }

    public Expense getExpense() {
        return expense;
    }

    public void setExpense(Expense expense) {
        this.expense = expense;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "ExpenseJoinEmployee{" +
                "expense=" + expense +
                ", employee=" + employee +
                '}';
    }
}
