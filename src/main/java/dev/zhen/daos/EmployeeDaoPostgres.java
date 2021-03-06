package dev.zhen.daos;

import dev.zhen.entities.Employee;
import dev.zhen.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EmployeeDaoPostgres implements EmployeeDAO{
    @Override
    public Employee getEmployeeByUsername(String username, String password) {
        try (Connection connection = ConnectionUtil.createConnection()){
            String sql = "select * from employee where username = ? and password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username.toLowerCase());
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int employeeId = resultSet.getInt("employee_id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                boolean isManager = resultSet.getBoolean("is_manager");
                Employee employee = new Employee(employeeId, username.toLowerCase(), password, firstName, lastName, isManager);
                return employee;
            } else
                return null;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
    }

    @Override
    public Employee getEmployeeById(int employeeId) {
        try (Connection connection = ConnectionUtil.createConnection()){
            String sql = "select * from employee where employee_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, employeeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                boolean isManager = resultSet.getBoolean("is_manager");
                Employee employee = new Employee(employeeId, username, password, firstName, lastName, isManager);
                return employee;
            } else
                return null;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Employee> getAllEmployee() {
        return null;
    }
}
