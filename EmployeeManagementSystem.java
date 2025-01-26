import java.util.*;
class Person {
    private String name;
    private int id;

    public Person(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
class Employee extends Person {
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
        return "ID: " + getId() + ", Name: " + getName() + ", Role: " + role + ", Department: " + department + ", Salary: " + salary;
    }
}
class Manager extends Employee {
    private double bonus;

    public Manager(String name, int id, String department, double salary, double bonus) {
        super(name, id, "Manager", department, salary);
        this.bonus = bonus;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    @Override
    public String toString() {
        return super.toString() + ", Bonus: " + bonus;
    }
}

public class EmployeeManagementSystem {
    private static List<Employee> employees = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Employee Management System ---");
            System.out.println("1. Add Employee");
            System.out.println("2. View All Employees");
            System.out.println("3. Search Employee by ID");
            System.out.println("4. Delete Employee");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 
            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    viewAllEmployees();
                    break;
                case 3:
                    searchEmployeeById();
                    break;
                case 4:
                    deleteEmployee();
                    break;
                case 5:
                    System.out.println("Exiting system. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void addEmployee() {
        System.out.print("Enter Employee Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Employee ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Enter Role (Employee/Manager): ");
        String role = scanner.nextLine();

        System.out.print("Enter Department: ");
        String department = scanner.nextLine();

        System.out.print("Enter Salary: ");
        double salary = scanner.nextDouble();

        if (role.equalsIgnoreCase("Manager")) {
            System.out.print("Enter Bonus: ");
            double bonus = scanner.nextDouble();
            employees.add(new Manager(name, id, department, salary, bonus));
        } else {
            employees.add(new Employee(name, id, role, department, salary));
        }

        System.out.println("Employee added successfully!");
    }

    private static void viewAllEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }

        System.out.println("\n--- Employee List ---");
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    private static void searchEmployeeById() {
        System.out.print("Enter Employee ID to search: ");
        int id = scanner.nextInt();

        for (Employee employee : employees) {
            if (employee.getId() == id) {
                System.out.println("Employee Found: " + employee);
                return;
            }
        }

        System.out.println("Employee with ID " + id + " not found.");
    }

    private static void deleteEmployee() {
        System.out.print("Enter Employee ID to delete: ");
        int id = scanner.nextInt();
        Iterator<Employee> iterator = employees.iterator();
        while (iterator.hasNext()) {
            Employee employee = iterator.next();
            if (employee.getId() == id) {
                iterator.remove();
                System.out.println("Employee with ID " + id + " deleted successfully.");
                return;
            }
        }

        System.out.println("Employee with ID " + id + " not found.");
    }
}
