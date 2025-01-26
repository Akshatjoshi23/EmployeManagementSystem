# Employee Management System

## Overview
The **Employee Management System** is a console-based application built using Java. It demonstrates core Object-Oriented Programming (OOP) principles such as encapsulation, inheritance, polymorphism, and abstraction. This project is suitable for BTech students or anyone looking to showcase their Java and OOP skills.

## Features
- **Add Employees**:
  - Add new employees with details such as name, ID, role, department, and salary.
  - Includes support for Manager-specific attributes like bonuses.
- **View All Employees**:
  - Display a list of all employees in the system.
- **Search Employee by ID**:
  - Search for an employee's details using their unique ID.
- **Delete Employee**:
  - Remove an employee from the system using their ID.
- **Role and Department Tracking**:
  - Categorize employees by their roles and departments.
- **File-based Persistence** (optional):
  - Extend the system to store employee data in files for persistence.

## Technologies Used
- **Java**:
  - Core Java concepts, including Collections and Exception Handling.
- **OOP Principles**:
  - Encapsulation: Private fields with public getters and setters.
  - Inheritance: Base `Person` class extended by `Employee` and `Manager` classes.
  - Polymorphism: Overridden methods for specialized behaviors.

## Class Structure
### 1. `Person`
Base class with common attributes like:
- Name
- ID

### 2. `Employee`
Derived class extending `Person` with additional attributes:
- Role
- Department
- Salary

### 3. `Manager`
Specialized class extending `Employee` with:
- Bonus attribute

## Usage
1. Upon running the program, you will see a menu with options to:
   - Add employees.
   - View all employees.
   - Search for an employee by ID.
   - Delete an employee.
   - Exit the program.
2. Follow the prompts to interact with the system.

## Example Interaction
```
--- Employee Management System ---
1. Add Employee
2. View All Employees
3. Search Employee by ID
4. Delete Employee
5. Exit
Choose an option: 1
Enter Employee Name: John Doe
Enter Employee ID: 101
Enter Role (Employee/Manager): Manager
Enter Department: HR
Enter Salary: 50000
Enter Bonus: 10000
Employee added successfully!
```

## Contributions
Feel free to fork this repository, make improvements, and create a pull request. Suggestions and enhancements are always welcome!

## Contact
For any questions or issues, feel free to reach out:
- **Email**: joshiakshat54@gmail.com
- **GitHub**: https://github.com/Akshatjoshi23

