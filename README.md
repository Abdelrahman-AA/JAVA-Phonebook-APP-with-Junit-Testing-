# JAVA-Phonebook-APP-with-Junit-Testing-
JAVA Phonebook APP with Junit (Testing)

# JAVA Phonebook APP with Junit (Testing)

---

## Project Description

This Phonebook application is developed using **Java** and **SQLite** for managing phone numbers. The application allows users to add, update, delete phone numbers, and search for phone numbers by name or email.

All features of the application are tested using **JUnit Jupiter** in **Java** to ensure functionality and quality. The test results and code coverage are documented using tools such as **Jacoco** and **Maven Surefire**.

Additionally, an **Excel** file has been created that documents all the test cases performed and their results.

---

## Tools Used

- **Java** - The main programming language used for the application development.
- **SQLite** - A local database to store phone number data.
- **JUnit Jupiter** - A testing framework used for unit testing in Java.
- **Jacoco** - A tool for generating code coverage reports.
- **Maven** - A build automation tool used for running tests and generating reports.
- **Excel** - Used for documenting all the test cases.

---

## Source Code Files

### 1. Main Application Code
- **`PhonebookApp.java`**  
  The main application logic, including the menu and user interaction.
  - **Path:**  
    `src/main/java/com/phonebook/PhonebookApp.java`

### 2. Database Manager
- **`DatabaseManager.java`**  
  Handles all database-related operations such as creating tables, adding, updating, and deleting records.
  - **Path:**  
    `src/main/java/com/phonebook/DatabaseManager.java`

---

## Test Files

### 1. JUnit Test Cases
- **`PhonebookAppTest.java`**  
  Contains unit tests for the main application functionality.
  - **Path:**  
    `src/test/java/com/phonebook/PhonebookAppTest.java`

- **`DatabaseManagerTest.java`**  
  Contains unit tests for database operations.
  - **Path:**  
    `src/test/java/com/phonebook/DatabaseManagerTest.java`

---

## Reports

### 1. Code Coverage Report
- **Jacoco Code Coverage Report**  
  Provides detailed insights into the code coverage percentage of the unit tests.
  - **Path:**  
    `target/site/jacoco/index.html`  
    (Open this file in a browser to view the report.)

### 2. Test Results Report
- **Maven Surefire Report**  
  Contains the results of all test cases executed during the build.
  - **Path:**  
    `target/surefire-reports/`  
    (Includes `.txt` and `.xml` files summarizing test results.)

---

## Test Cases

### 1. Detailed Test Cases
- **`Test Cases.xlsx`**  
  An Excel file documenting all test cases, their steps, expected results, actual results, and status.
  - **Path:**  
    `PhonebookApp/Test Cases.xlsx`

---

## How to Run the Project

1. **Ensure you have the following tools installed:**
   - **Java Development Kit (JDK)** version 11 or above.
   - **Maven** for managing dependencies and running tests.
   - **SQLite** to ensure the database functions correctly.

2. **Clone the repository:**
   Clone the repository to your local machine using the following command:
   ```bash
   git clone https://github.com/Abdelrahman-AA/JAVA-Phonebook-APP-with-Junit-Testing-.git
