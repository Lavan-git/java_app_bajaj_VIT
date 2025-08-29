package com.bajaj.finserv.service;

import com.bajaj.finserv.dto.WebhookResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class StartupService {

    @Autowired
    private WebhookService webhookService;

    @Autowired
    private SqlSolutionService sqlSolutionService;

    @EventListener(ApplicationReadyEvent.class)
    public void executeStartupFlow() {
        System.out.println("==========================================");
        System.out.println("Starting Bajaj Finserv Health Qualifier");
        System.out.println("==========================================");

        try {
            // Step 1: Generate webhook
            System.out.println("Step 1: Generating webhook...");
            WebhookResponse webhookResponse = webhookService.generateWebhook();

            // Step 2: Solve SQL problem
            System.out.println("Step 2: Solving SQL problem...");
            String finalQuery = sqlSolutionService.solveSqlProblem();

            // Step 3: Submit solution
            System.out.println("Step 3: Submitting solution...");
            webhookService.submitSolution(finalQuery, webhookResponse.getAccessToken());

            System.out.println("==========================================");
            System.out.println("Application flow completed successfully!");
            System.out.println("==========================================");

        } catch (Exception e) {
            System.err.println("Error in startup flow: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
