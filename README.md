# COMP4442 Calculator Service

## Project Overview

This project is a simple calculator web application built for a COMP4442 course demo. It provides a plain HTML/CSS/JavaScript frontend, a Spring Boot backend, and MySQL database storage for calculation history.

Deployed application:

```text
http://3.137.148.141:8080/
```

Deployed branch:

```text
3tier
```

## Main Features

- Calculator operations:
  - Add
  - Subtract
  - Multiply
  - Divide
  - Square
  - Square Root
  - Sum
  - Average
  - Min
  - Max
  - Power
  - Percent
- JSON API responses for calculations
- Input validation and friendly error messages
- Persistent calculation history in MySQL
- Calculation statistics based on saved history
- Simple request logging filter
- Frontend page at `/`

## 3-Tier Architecture

This project follows a simple 3-tier architecture:

1. Frontend layer
   - File: `src/main/resources/static/index.html`
   - Uses plain HTML, CSS, and JavaScript `fetch`
   - Calls backend REST endpoints

2. Backend layer
   - Spring Boot application
   - Controller: `CalculatorController`
   - Service: `CalculatorService`
   - Repository: `CalculationHistoryRepository`
   - Uses Spring JDBC / `JdbcTemplate`

3. Database layer
   - MySQL database
   - Database name: `calculator_db`
   - Table: `calculation_history`
   - Schema file: `src/main/resources/schema.sql`

## Local Setup

Prerequisites:

- Java 17
- Maven wrapper included in the project
- MySQL running locally

Clone the repository and enter the project folder:

```powershell
git clone <repository-url>
cd comp4442-calculator
```

Set database environment variables:

PowerShell:

```powershell
$env:DB_URL="jdbc:mysql://localhost:3306/calculator_db"
$env:DB_USERNAME="calculator_user"
$env:DB_PASSWORD="mysql_password"
```

Windows CMD:

```cmd
set DB_URL=jdbc:mysql://localhost:3306/calculator_db
set DB_USERNAME=calculator_user
set DB_PASSWORD=mysql_password
```

Run the application:

```powershell
.\mvnw.cmd spring-boot:run
```

Open the frontend:

```text
http://localhost:8080/
```

## MySQL Setup

Enter MySQL:

```bash
sudo mysql
```

Create the database:

```sql
CREATE DATABASE IF NOT EXISTS calculator_db;
```

Create a MySQL user and grant access using a placeholder password:

```sql
CREATE USER IF NOT EXISTS 'calculator_user'@'localhost' IDENTIFIED BY 'mysql_password';
GRANT ALL PRIVILEGES ON calculator_db.* TO 'calculator_user'@'localhost';
FLUSH PRIVILEGES;
```

Exit MySQL:

```sql
exit;
```

The application uses `schema.sql` to create the history table if it does not already exist:

```sql
CREATE TABLE IF NOT EXISTS calculation_history (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    operation VARCHAR(50) NOT NULL,
    input TEXT NOT NULL,
    result VARCHAR(255) NOT NULL,
    created_at VARCHAR(64) NOT NULL
);
```

Database configuration is read from environment variables:

```properties
DB_URL
DB_USERNAME
DB_PASSWORD
```


## AWS EC2 Deployment Steps

Deployment target:

```text
http://3.137.148.141:8080/
```

Basic deployment flow:

1. Launch an EC2 instance.
2. Install Java 17, Git, and MySQL on the EC2 instance:

```bash
sudo apt update
sudo apt install openjdk-17-jdk git mysql-server -y
sudo systemctl start mysql
```

3. Clone the project repository on EC2.
4. Switch to the deployed branch:

```bash
git checkout 3tier
```

5. Make the Maven wrapper executable:

```bash
chmod +x mvnw
```

6. Enter MySQL:

```bash
sudo mysql
```

7. Create the MySQL database:

```sql
CREATE DATABASE IF NOT EXISTS calculator_db;
```

8. Create a MySQL user and grant access:

```sql
CREATE USER IF NOT EXISTS 'calculator_user'@'localhost' IDENTIFIED BY 'mysql_password';
GRANT ALL PRIVILEGES ON calculator_db.* TO 'calculator_user'@'localhost';
FLUSH PRIVILEGES;
```

9. Exit MySQL:

```sql
exit;
```

10. Set environment variables on EC2:

```bash
export DB_URL="jdbc:mysql://localhost:3306/calculator_db"
export DB_USERNAME="calculator_user"
export DB_PASSWORD="mysql_password"
```

11. Build the application:

```bash
./mvnw clean package -DskipTests
```

12. Check the actual jar name:

```bash
ls target/*.jar
```

13. Run the packaged application in the background. Replace `<jar-file>` with the jar shown by the previous command:

```bash
nohup java -jar target/<jar-file> > app.log 2>&1 &
```

14. Check the application log:

```bash
tail -f app.log
```

15. Configure the EC2 security group:

- Allow SSH `22` from `My IP`.
- Allow application port `8080` from demo users or `Anywhere IPv4`.
- Do not expose MySQL `3306` publicly.

After an EC2 reboot, the Spring Boot app must be started again before testing `/history` and `/stats`, unless a system service, such as `systemd`, is configured.

The EC2 public IP may change after Stop/Start unless an Elastic IP is used.


## How To Test The Deployed System

Open the frontend:

```text
http://3.137.148.141:8080/
```

Test a calculation:

```text
http://3.137.148.141:8080/add?a=2&b=3
```

Expected result includes:

```json
{
  "operation": "add",
  "result": 5
}
```

Test history:

```text
http://3.137.148.141:8080/history
```

Test stats:

```text
http://3.137.148.141:8080/stats
```

Clear history:

```bash
curl -X DELETE http://3.137.148.141:8080/history/clear
```

`/history/clear` is for demo and testing only because authentication is not implemented.

Restart Spring Boot or reboot the EC2 instance, then check:

```text
http://3.137.148.141:8080/history
http://3.137.148.141:8080/stats
```

History and stats should still work because records are stored persistently in MySQL.