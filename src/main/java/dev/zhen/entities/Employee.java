package dev.zhen.entities;

public class Employee {
    private int employeeId;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private boolean isManager;

    public Employee(int employeeId, String account, String password, String firstName, String lastName, boolean isManager) {
        this.setEmployeeId(employeeId);
        this.setUsername(account);
        this.setPassword(password);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setManager(isManager);
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isManager() {
        return isManager;
    }

    public void setManager(boolean manager) {
        isManager = manager;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", account='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", isManager=" + isManager +
                '}';
    }
}
