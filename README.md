# Bajaj Finserv Health Qualifier - Java

This Spring Boot application was developed for the Bajaj Finserv Health Java Qualifier challenge.

## Overview

The application:
1. Sends a POST request to generate a webhook on startup
2. Solves a SQL problem based on registration number (REG12347 - odd, so Question 1)
3. Submits the SQL solution to the webhook URL using JWT authentication

## SQL Problem

**Question 1 (for odd registration numbers):**
Find the highest salary (not on 1st day) with employee details.

**Solution Query:**
```sql
SELECT 
    p.AMOUNT AS SALARY,
    CONCAT(e.FIRST_NAME, ' ', e.LAST_NAME) AS NAME,
    TIMESTAMPDIFF(YEAR, e.DOB, CURDATE()) AS AGE,
    d.DEPARTMENT_NAME
FROM PAYMENTS p
JOIN EMPLOYEE e ON p.EMP_ID = e.EMP_ID
JOIN DEPARTMENT d ON e.DEPARTMENT = d.DEPARTMENT_ID
WHERE DAY(p.PAYMENT_TIME) <> 1
ORDER BY p.AMOUNT DESC
LIMIT 1
```

## How to Run

### Prerequisites
- Java 17 or higher
- Maven 3.6 or higher

### Build and Run
```bash
mvn clean package
java -jar target/finserv-health-qualifier-1.0.0.jar
```

## Project Structure
- `WebhookService` - Handles API calls for webhook generation and solution submission
- `SqlSolutionService` - Contains the SQL solution logic
- `StartupService` - Coordinates the application flow on startup
- DTOs - Data transfer objects for API requests/responses

## API Endpoints Used
- POST `https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA`
- POST `https://bfhldevapigw.healthrx.co.in/hiring/testWebhook/JAVA`

## Author
Developed for Bajaj Finserv Health Qualifier Task
