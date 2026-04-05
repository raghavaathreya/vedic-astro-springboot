package com.example.demo.service;

import com.example.demo.model.KundliResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import org.springframework.boot.web.client.RestTemplateBuilder;
import java.time.Duration;
import jakarta.annotation.PostConstruct;  // ← CHANGED THIS

import java.util.Map;

@Service
public class AstrologyService {

    @Autowired
    private GroqService groqService;

    @Value("${python.api.url}")
    private String PYTHON_API_URL;

    @PostConstruct
    public void init() {
        System.out.println("========================================");
        System.out.println("PYTHON_API_URL configured as: " + PYTHON_API_URL);
        System.out.println("========================================");
    }

    public KundliResponse calculateKundli(
        int year, int month, int day,
        int hour, int minute,
        double latitude, double longitude
    ) {
        RestTemplate restTemplate = new RestTemplateBuilder()
            .setConnectTimeout(Duration.ofSeconds(60))
            .setReadTimeout(Duration.ofSeconds(60))
            .build();
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        String requestBody = String.format(
            "{\"year\": %d, \"month\": %d, \"day\": %d, \"hour\": %d, \"minute\": %d, \"latitude\": %f, \"longitude\": %f}",
            year, month, day, hour, minute, latitude, longitude
        );
        
        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
        
        System.out.println("=== CALLING PYTHON API ===");
        System.out.println("URL: " + PYTHON_API_URL);
        System.out.println("Request: " + requestBody);
        
        ResponseEntity<KundliResponse> response = restTemplate.exchange(
            PYTHON_API_URL,
            HttpMethod.POST,
            request,
            KundliResponse.class
        );
        
        KundliResponse kundliResponse = response.getBody();
        
        System.out.println("=== PYTHON API RESPONSE RECEIVED ===");
        
        try {
            System.out.println("=== CALLING GROQ SERVICE ===");
            
            Map<String, Object> aiInsights = groqService.generateKundliInsights(
                kundliResponse.getRashi(),
                kundliResponse.getNakshatra(),
                kundliResponse.getLagna(),
                kundliResponse.getPlanets()
            );
            
            System.out.println("AI Insights generated successfully!");
            kundliResponse.setAiInsights(aiInsights);
        } catch (Exception e) {
            System.err.println("=== AI INSIGHTS GENERATION FAILED ===");
            e.printStackTrace();
        }
        
        return kundliResponse;
    }
}