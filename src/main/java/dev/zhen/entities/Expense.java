package dev.zhen.entities;

import dev.zhen.enums.Status;

public class Expense {
    private int expenseId;
    private int employeeId;
    private double amount;
    private String reason;
    private Status status;
    private long dateSubmitted;
    private long dateProcessed;

    public Expense(int expenseId, int employeeId, double amount, String reason, Status status, long dateSubmitted, long dateProcessed) {
        this.expenseId = expenseId;
        this.employeeId = employeeId;
        this.amount = amount;
        this.reason = reason;
        this.status = status;
        this.dateSubmitted = dateSubmitted;
        this.dateProcessed = dateProcessed;
    }

    public int getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(int expenseId) {
        this.expenseId = expenseId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public long getDateSubmitted() {
        return dateSubmitted;
    }

    public void setDateSubmitted(long dateSubmitted) {
        this.dateSubmitted = dateSubmitted;
    }

    public long getDateProcessed() {
        return dateProcessed;
    }

    public void setDateProcessed(long dateProcessed) {
        this.dateProcessed = dateProcessed;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "expenseId=" + expenseId +
                ", employeeId=" + employeeId +
                ", amount=" + amount +
                ", reason='" + reason + '\'' +
                ", status=" + status +
                ", dateSubmitted=" + dateSubmitted +
                ", dateProcessed=" + dateProcessed +
                '}';
    }
}
