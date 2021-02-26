package dev.zhen.daos;

import dev.zhen.customException.ExpenseNotFoundException;
import dev.zhen.entities.Expense;
import dev.zhen.enums.Status;
import dev.zhen.utils.ConnectionUtil;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class ExpenseDaoPostgres implements ExpenseDAO{

    private static Logger logger = Logger.getLogger(ExpenseDaoPostgres.class.getName());

    @Override
    public Expense createExpense(int employeeId, Expense expense) {
        try (Connection connection = ConnectionUtil.createConnection()) {
            String sql = "insert into expense (employee_id, amount, reason, status, date_submitted, date_processed) values (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, employeeId);
            preparedStatement.setDouble(2, expense.getAmount());
            preparedStatement.setString(3, expense.getReason());
            preparedStatement.setInt(4, expense.getStatus().ordinal());
            preparedStatement.setLong(5, expense.getDateSubmitted());
            preparedStatement.setLong(6, expense.getDateProcessed());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            expense.setEmployeeId(employeeId);
            expense.setExpenseId(resultSet.getInt("expense_id"));
            return expense;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            logger.error("Failed to create expense", sqlException);
            return null;
        }
    }

    @Override
    public List<Expense> getAllExpense() {
        try (Connection connection = ConnectionUtil.createConnection()) {
            String sql = "select * from expense";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Expense> resultList = new LinkedList<>();
            while(resultSet.next()) {
                int expenseId = resultSet.getInt("expense_id");
                int employeeId = resultSet.getInt("employee_id");
                double amount = resultSet.getDouble("amount");
                String reason = resultSet.getString("reason");
                Status status = Status.values()[resultSet.getInt("status")];
                long dateSubmitted = resultSet.getLong("date_submitted");
                long dateProcessed = resultSet.getLong("date_processed");
                Expense expense = new Expense(expenseId, employeeId, amount, reason, status, dateSubmitted, dateProcessed);
                resultList.add(expense);
            }
            return resultList;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            logger.error("Failed to get all expense", sqlException);
            return null;
        }
    }

    @Override
    public List<Expense> getAllExpenseByEmployeeId(int employeeId) {
        try (Connection connection = ConnectionUtil.createConnection()) {
            String sql = "select * from expense where employee_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, employeeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Expense> resultList = new LinkedList<>();
            while(resultSet.next()) {
                int expenseId = resultSet.getInt("expense_id");
                double amount = resultSet.getDouble("amount");
                String reason = resultSet.getString("reason");
                Status status = Status.values()[resultSet.getInt("status")];
                long dateSubmitted = resultSet.getLong("date_submitted");
                long dateProcessed = resultSet.getLong("date_processed");
                Expense expense = new Expense(expenseId, employeeId, amount, reason, status, dateSubmitted, dateProcessed);
                resultList.add(expense);
            }
            return resultList;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            logger.error("Failed to get all expense by employee id", sqlException);
            return null;
        }
    }

    @Override
    public Expense getExpenseById(int id) {
        try (Connection connection = ConnectionUtil.createConnection()) {
            String sql = "select * from expense where expense_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int expenseId = resultSet.getInt("expense_id");
                int employeeId = resultSet.getInt("employee_id");
                double amount = resultSet.getDouble("amount");
                String reason = resultSet.getString("reason");
                Status status = Status.values()[resultSet.getInt("status")];
                long dateSubmitted = resultSet.getLong("date_submitted");
                long dateProcessed = resultSet.getLong("date_processed");
                Expense expense = new Expense(expenseId, employeeId, amount, reason, status, dateSubmitted, dateProcessed);
                return expense;
            } else
                return null;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            logger.error("Failed to get expense by id", sqlException);
            return null;
        }
    }

    @Override
    public Expense updateExpenseById(int id, Expense expense) throws ExpenseNotFoundException {
        Expense oldExpense = getExpenseById(id);
        if (oldExpense == null)
            throw new ExpenseNotFoundException("Expense with id " + id + " is not found");
        else {
            try (Connection connection = ConnectionUtil.createConnection()) {
                String sql = "update expense set employee_id = ?, amount = ?, reason = ?, status = ?, date_submitted = ?, date_processed = ? where expense_id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, expense.getEmployeeId());
                preparedStatement.setDouble(2, expense.getAmount());
                preparedStatement.setString(3, expense.getReason());
                preparedStatement.setInt(4, expense.getStatus().ordinal());
                preparedStatement.setLong(5, expense.getDateSubmitted());
                preparedStatement.setLong(6, expense.getDateProcessed());
                preparedStatement.setInt(7, id);
                preparedStatement.execute();
                expense.setExpenseId(id);
                return expense;
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
                logger.error("Failed to update expense", sqlException);
                return null;
            }
        }
    }

    @Override
    public boolean deleteExpenseById(int id) throws ExpenseNotFoundException{
        Expense oldExpense = getExpenseById(id);
        if (oldExpense == null)
            throw new ExpenseNotFoundException("Expense with id " + id + " is not found");
        else {
            try (Connection connection = ConnectionUtil.createConnection()) {
                String sql = "delete from expense where expense_id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, id);
                preparedStatement.execute();
                return true;
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
                logger.error("Failed to delete expense", sqlException);
                return false;
            }
        }
    }
}
