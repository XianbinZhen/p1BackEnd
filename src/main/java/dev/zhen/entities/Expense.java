package dev.zhen.entities;

import dev.zhen.enums.Status;

import javax.persistence.*;

@Entity
@Table(name = "expense")
public class Expense {
    @Id
    @Column(name = "expense_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int expenseId;
    @Column(name = "employee_id")
    private int employeeId;
    @Column(name = "amount")
    private double amount;
    @Column(name = "reason")
    private String reason;
    @Column(name = "status")
    private Status status;
    @Column(name = "date_submitted")
    private long dateSubmitted;
    @Column(name = "date_processed")
    private long dateProcessed;
    @Column(name = "img_url")
    private String imgUrl;

    public Expense() {
    }

    public Expense(int expenseId, int employeeId, double amount, String reason, Status status, long dateSubmitted, long dateProcessed, String imgUrl) {
        this.expenseId = expenseId;
        this.employeeId = employeeId;
        this.amount = amount;
        this.reason = reason;
        this.status = status;
        this.dateSubmitted = dateSubmitted;
        this.dateProcessed = dateProcessed;
        this.imgUrl = imgUrl;
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


    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
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
                ", imgUrl='" + imgUrl + '\'' +
                '}';
    }
}
