package com.bajaj.finserv.service;

import com.bajaj.finserv.dto.SolutionRequest;
import com.bajaj.finserv.dto.WebhookRequest;
import com.bajaj.finserv.dto.WebhookResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WebhookService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String WEBHOOK_GENERATE_URL = "https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA";
    private static final String WEBHOOK_SUBMIT_URL = "https://bfhldevapigw.healthrx.co.in/hiring/testWebhook/JAVA";

    public WebhookResponse generateWebhook() {
        try {
            // Create request body
            WebhookRequest request = new WebhookRequest("John Doe", "REG12347", "john@example.com");

            // Set headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<WebhookRequest> entity = new HttpEntity<>(request, headers);

            // Make POST request
            ResponseEntity<WebhookResponse> response = restTemplate.exchange(
                WEBHOOK_GENERATE_URL,
                HttpMethod.POST,
                entity,
                WebhookResponse.class
            );

            System.out.println("Webhook generated successfully!");
            System.out.println("Webhook URL: " + response.getBody().getWebhook());
            System.out.println("Access Token: " + response.getBody().getAccessToken());

            return response.getBody();
        } catch (Exception e) {
            System.err.println("Error generating webhook: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to generate webhook", e);
        }
    }

    public void submitSolution(String finalQuery, String accessToken) {
        try {
            // Create request body
            SolutionRequest request = new SolutionRequest(finalQuery);

            // Set headers with JWT token
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", accessToken);

            HttpEntity<SolutionRequest> entity = new HttpEntity<>(request, headers);

            // Make POST request
            ResponseEntity<String> response = restTemplate.exchange(
                WEBHOOK_SUBMIT_URL,
                HttpMethod.POST,
                entity,
                String.class
            );

            System.out.println("Solution submitted successfully!");
            System.out.println("Response: " + response.getBody());
        } catch (Exception e) {
            System.err.println("Error submitting solution: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to submit solution", e);
        }
    }
}
