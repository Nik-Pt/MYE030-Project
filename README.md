# ⚽ Football Statistics Web App

### **Team Members**
- **Pittas Nikolaos** – AM: 5007  
- **Kallipolitis Apollon Petros** – AM: 4963  
- **Giannakos Ioannis** – AM: 4970  

---

## 📌 Project Overview

This is a **Database Management Systems (DBMS)** project built using **Java**, **Spring Boot**, and **Python**. The application retrieves football-related data from a MySQL database and displays:

- Player statistics  
- Country-specific statistics  
- General statistics by year

---

## 🚀 Getting Started

Follow these steps to set up and run the project locally:

---

### ✅ Prerequisites

Make sure you have the following installed:

- [Java](https://www.oracle.com/java/technologies/javase-downloads.html) (JDK 17 or later)
- [Python](https://www.python.org/downloads/) (3.x)
- [MySQL](https://dev.mysql.com/downloads/installer/)

---

### 🔧 Step 1: Install Python Packages

Open **Command Prompt** and run:

```bash
pip install pandas
pip install mysql-connector-python
pip install Unidecode
```

---

### ⚙️ Step 2: Configure MySQL

Open **MySQL Command Line Client** and run:

```sql
SET GLOBAL local_infile = 1;
```

This enables loading data from local files, which is required by the data-loading scripts.

---

### 🗃️ Step 3: Load Data into the Database

Run the following Python script to:

- Create the database schema
- Load CSV data into tables

```bash
python csvToMySQL.py
```

> ⚠️ **Note:** Make sure MySQL is running and using the **default user/password** (e.g., `root`/`root`). If not, you may need to update the credentials inside `csvToMySQL.py` and the Spring Boot configuration.

---

### 🧪 Step 4: Run the Spring Boot Application

1. Open your preferred IDE (e.g., **Eclipse**, **IntelliJ**, or **VS Code**).
2. Import the Java project.
3. Run the `main` class to start the Spring Boot server.

---

### 🌐 Step 5: Access the Application

Open a browser and go to:

```text
http://localhost:8080
```

You should see the web interface with football statistics.

---

## 📝 Troubleshooting

- If you encounter issues with **database connection**, check your MySQL username and password in:
  - `application.properties` (Java)
  - `csvToMySQL.py` (Python)
- Make sure `local_infile` is enabled in MySQL.
- Ensure all required Python modules are installed.

---

## 📂 Folder Structure

```
project-root/
├── Data/                                 # CSV files to be loaded
├── resultsFinder/resultsFinder           # Java Spring Boot application
├── DB_backup_and_schema                  # DB related folder
├── Scripts/                              # Scripts to create/populate the database
├── README.md                             # This file
```
