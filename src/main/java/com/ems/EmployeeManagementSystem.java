package com.ems;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class EmployeeManagementSystem {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Starting Employee Management System with embedded H2 database...");
        try (EmployeeDao dao = new EmployeeDao()) {
            while (true) {
                printMenu();
                int option = readInt("Choose an option: ");

                switch (option) {
                    case 1 -> addEmployee(dao);
                    case 2 -> viewAllEmployees(dao);
                    case 3 -> searchEmployeeById(dao);
                    case 4 -> deleteEmployee(dao);
                    case 5 -> {
                        System.out.println("Exiting system. Goodbye!");
                        return;
                    }
                    default -> System.out.println("Invalid option. Please choose a number between 1 and 5.");
                }
            }
        } catch (SQLException ex) {
            System.out.println("Database error: " + ex.getMessage());
        }
    }

    private static void printMenu() {
        System.out.println("\n--- Employee Management System ---");
        System.out.println("1. Add Employee");
        System.out.println("2. View All Employees");
        System.out.println("3. Search Employee by ID");
        System.out.println("4. Delete Employee");
        System.out.println("5. Exit");
    }

    private static void addEmployee(EmployeeDao dao) {
        String name = readString("Enter Employee Name: ");
        int id = readInt("Enter Employee ID: ");
        String role = readString("Enter Role (Employee/Manager): ");
        String department = readString("Enter Department: ");
        double salary = readDouble("Enter Salary: ");

        Employee employee;
        if (role.equalsIgnoreCase("Manager")) {
            double bonus = readDouble("Enter Bonus: ");
            employee = new Manager(name, id, department, salary, bonus);
        } else {
            employee = new Employee(name, id, role, department, salary);
        }

        try {
            boolean created = dao.addEmployee(employee);
            if (created) {
                System.out.println("Employee added successfully!");
            } else {
                System.out.println("Employee ID " + id + " already exists. Please choose a different ID.");
            }
        } catch (SQLException ex) {
            System.out.println("Unable to save employee: " + ex.getMessage());
        }
    }

    private static void viewAllEmployees(EmployeeDao dao) {
        try {
            List<Employee> employees = dao.getAllEmployees();
            if (employees.isEmpty()) {
                System.out.println("No employees found.");
                return;
            }
            System.out.println("\n--- Employee List ---");
            employees.forEach(System.out::println);
        } catch (SQLException ex) {
            System.out.println("Unable to read employees: " + ex.getMessage());
        }
    }

    private static void searchEmployeeById(EmployeeDao dao) {
        int id = readInt("Enter Employee ID to search: ");
        try {
            Employee employee = dao.findEmployeeById(id);
            if (employee == null) {
                System.out.println("Employee with ID " + id + " not found.");
            } else {
                System.out.println("Employee Found: " + employee);
            }
        } catch (SQLException ex) {
            System.out.println("Unable to search employee: " + ex.getMessage());
        }
    }

    private static void deleteEmployee(EmployeeDao dao) {
        int id = readInt("Enter Employee ID to delete: ");
        try {
            if (dao.deleteEmployeeById(id)) {
                System.out.println("Employee with ID " + id + " deleted successfully.");
            } else {
                System.out.println("Employee with ID " + id + " not found.");
            }
        } catch (SQLException ex) {
            System.out.println("Unable to delete employee: " + ex.getMessage());
        }
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                String line = scanner.nextLine().trim();
                return Integer.parseInt(line);
            } catch (NumberFormatException ex) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                String line = scanner.nextLine().trim();
                return Double.parseDouble(line);
            } catch (NumberFormatException ex) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static String readString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isBlank()) {
                return input;
            }
            System.out.println("Input may not be blank.");
        }
    }
}
