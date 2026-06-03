# Employee Management System

## Overview
This is a Java console-based Employee Management System with an embedded H2 database for persistence. It now includes a cleaner architecture with separate model classes, a DAO layer, and robust input handling.

## Features
- Add new employees and managers
- View all employees stored in the database
- Search employee records by ID
- Delete employee records by ID
- Persistent storage using H2 embedded database
- Improved OOP structure and database separation

## Architecture
- `Person` — base class for shared identity data
- `Employee` — derives from `Person` and adds role, department, and salary
- `Manager` — extends `Employee` with bonus support
- `EmployeeDao` — handles all database operations using JDBC
- `EmployeeManagementSystem` — console UI and program flow

## Technologies Used
- Java 17
- Maven
- H2 embedded database
- JDBC

## Run the project
1. Open the project folder in your IDE or terminal.
2. Build the project with Maven:
   ```bash
   mvn clean package
   ```
3. Run the application:
   ```bash
   mvn exec:java -Dexec.mainClass="com.ems.EmployeeManagementSystem"
   ```

> If you do not have `maven-exec-plugin` configured, you can also run the compiled class directly with:
> ```bash
> java -cp target/classes;path/to/h2.jar com.ems.EmployeeManagementSystem
> ```

## Notes
- The embedded H2 database files are stored in the `data/` folder.
- Database artifacts are ignored in Git via `.gitignore`.

## GitHub
This project is prepared for version control with Git and can be uploaded to GitHub as an improved resume project.

