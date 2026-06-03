package com.ems;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao implements AutoCloseable {
    private static final String JDBC_URL = "jdbc:h2:./data/employees;AUTO_SERVER=TRUE";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    private final Connection connection;

    public EmployeeDao() throws SQLException {
        connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
        initializeDatabase();
    }

    private void initializeDatabase() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS employees ("
                    + "id INT PRIMARY KEY, "
                    + "name VARCHAR(100) NOT NULL, "
                    + "role VARCHAR(50) NOT NULL, "
                    + "department VARCHAR(50) NOT NULL, "
                    + "salary DOUBLE NOT NULL, "
                    + "bonus DOUBLE, "
                    + "is_manager BOOLEAN NOT NULL"
                    + ")");
        }
    }

    public boolean addEmployee(Employee employee) throws SQLException {
        if (findEmployeeById(employee.getId()) != null) {
            return false;
        }

        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO employees (id, name, role, department, salary, bonus, is_manager) VALUES (?, ?, ?, ?, ?, ?, ?)") ) {
            statement.setInt(1, employee.getId());
            statement.setString(2, employee.getName());
            statement.setString(3, employee.getRole());
            statement.setString(4, employee.getDepartment());
            statement.setDouble(5, employee.getSalary());
            statement.setDouble(6, employee instanceof Manager ? ((Manager) employee).getBonus() : 0.0);
            statement.setBoolean(7, employee instanceof Manager);
            statement.executeUpdate();
            return true;
        }
    }

    public List<Employee> getAllEmployees() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM employees ORDER BY id")) {
            while (resultSet.next()) {
                employees.add(mapRowToEmployee(resultSet));
            }
        }
        return employees;
    }

    public Employee findEmployeeById(int id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM employees WHERE id = ?")) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapRowToEmployee(resultSet);
                }
            }
        }
        return null;
    }

    public boolean deleteEmployeeById(int id) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM employees WHERE id = ?")) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        }
    }

    private Employee mapRowToEmployee(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String role = resultSet.getString("role");
        String department = resultSet.getString("department");
        double salary = resultSet.getDouble("salary");
        double bonus = resultSet.getDouble("bonus");
        boolean isManager = resultSet.getBoolean("is_manager");

        if (isManager) {
            return new Manager(name, id, department, salary, bonus);
        }
        return new Employee(name, id, role, department, salary);
    }

    @Override
    public void close() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
