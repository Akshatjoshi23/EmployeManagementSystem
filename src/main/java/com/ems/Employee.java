package com.ems;

public class Employee extends Person {
    private String role;
    private String department;
    private double salary;

    public Employee(String name, int id, String role, String department, double salary) {
        super(name, id);
        this.role = role;
        this.department = department;
        this.salary = salary;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return String.format("ID: %d, Name: %s, Role: %s, Department: %s, Salary: %.2f", getId(), getName(), role, department, salary);
    }
}
