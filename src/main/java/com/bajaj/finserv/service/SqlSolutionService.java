package com.bajaj.finserv.service;

import org.springframework.stereotype.Service;

@Service
public class SqlSolutionService {

    public String solveSqlProblem() {
        // Since REG12347 ends with 47 (odd number), we need to solve Question 1
        // Query: Find the highest salary (not on 1st day)
        
        String finalQuery = """
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
            LIMIT 1""";
        
        System.out.println("Solving SQL Problem for Question 1 (odd regNo)");
        System.out.println("Problem: Find the highest salary (not on 1st day)");
        System.out.println("Final Query: " + finalQuery);
        
        return finalQuery.trim();
    }
    
    private boolean isOddRegNo(String regNo) {
        // Extract last two digits and check if odd
        String lastTwoDigits = regNo.substring(regNo.length() - 2);
        int number = Integer.parseInt(lastTwoDigits);
        return number % 2 != 0;
    }
}
