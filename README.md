# NayePankh Management System (Java + JDBC + MySQL)
## 📖 Project Overview
NayePankh Management System is a Java-based desktop application designed to manage user login and volunteer registration using Java Swing (or AWT) for UI and MySQL database for backend storage. The project uses JDBC for database connectivity.
---
## 🚀 Features
- User Login System
- Volunteer Registration Form
- Data insertion into MySQL database
- Secure database connection using JDBC
- Simple and user-friendly GUI
---
## 🛠️ Technologies Used
- Java (Core Java)
- Java Swing / AWT (for UI)
- JDBC (Java Database Connectivity)
- MySQL Database
- VS Code / Eclipse (IDE)
---
## 📁 Project Structure
```text
NayePankhFoundationSystem/
│
├── nayepankh.sql
├── src/
│   └── main/
│       ├── Main.java
│       ├── database/
│       │   └── DBConnection.java
│       ├── model/
│       │   ├── Volunteer.java
│       │   └── Donation.java
│       ├── dao/
│       │   ├── VolunteerDAO.java
│       │   └── DonationDAO.java
│       └── gui/
│           ├── LoginFrame.java
│           ├── DashboardFrame.java
│           ├── VolunteerForm.java
│           ├── DonationForm.java
│           └── ReportFrame.java
│
├── lib/
│   └── mysql-connector-j.jar
│
└── .vscode/
    └── settings.json
```
## ⚙️ Setup Instructions
### 1. Install Requirements
- Install Java JDK (8 or above)
- Install MySQL Server or XAMPP
- Install VS Code or Eclipse
---
### 2. Add MySQL Connector
- Download MySQL Connector/J
- Add `.jar` file inside `lib/` folder
- Configure classpath in VS Code or IDE
---
### 3. Configure Database
- Change password and username in DBConnection file or whervever required
- Create database in MySQL:

```sql:
CREATE DATABASE nayepankh_db;
USE nayepankh_db;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL
);

INSERT INTO users(username,password)
VALUES('admin','admin123');

CREATE TABLE volunteers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    phone VARCHAR(15),
    email VARCHAR(100),
    city VARCHAR(50),
    skills VARCHAR(200)
);

CREATE TABLE donations (
    id INT AUTO_INCREMENT PRIMARY KEY,
    donor_name VARCHAR(100),
    email VARCHAR(100),
    amount DOUBLE,
    purpose VARCHAR(100),
    donation_date DATE
);
```
## 📸 Screenshots

### Login Page
<img width="2560" height="1440" alt="Screenshot (590)" src="https://github.com/user-attachments/assets/f44a5366-1d2f-43b0-a8db-2e90c4f37520" />


### Dashboard
<img width="2560" height="1440" alt="Screenshot (591)" src="https://github.com/user-attachments/assets/1f4804b0-8226-468c-b8e6-562e5fa8c0ca" />


### Donation Form
<img width="2560" height="1440" alt="Screenshot (593)" src="https://github.com/user-attachments/assets/4f1c131d-29b2-46f4-9f93-cc6b4fd46c7a" />


### Volunteer Management
<img width="2560" height="1440" alt="Screenshot (592)" src="https://github.com/user-attachments/assets/e4257e7b-32b3-4070-9f29-8a51327e1768" />

### Reports
<img width="2560" height="1440" alt="Screenshot (594)" src="https://github.com/user-attachments/assets/d3ad4263-1a5e-4bfc-8bda-f5467c2e6c88" />
