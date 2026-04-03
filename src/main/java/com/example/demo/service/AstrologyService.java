package com.example.demo.service;

import com.example.demo.model.KundliResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.Map;

@Service
public class AstrologyService {

@Autowired
private GroqService groqService;

    private final String PYTHON_API_URL = "http://localhost:5000/calculate";

    public KundliResponse calculateKundli(
        int year, int month, int day,
        int hour, int minute,
        double latitude, double longitude
    ) {
        RestTemplate restTemplate = new RestTemplate();
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        String requestBody = String.format(
            "{\"year\": %d, \"month\": %d, \"day\": %d, \"hour\": %d, \"minute\": %d, \"latitude\": %f, \"longitude\": %f}",
            year, month, day, hour, minute, latitude, longitude
        );
        
        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
        
        ResponseEntity<KundliResponse> response = restTemplate.exchange(
            PYTHON_API_URL,
            HttpMethod.POST,
            request,
            KundliResponse.class
        );
        
        KundliResponse kundliResponse = response.getBody();
        
        // THIS SECTION SHOULD BE HERE - Generate AI insights
        try {
            System.out.println("=== CALLING OPENAI SERVICE ===");
            System.out.println("Rashi: " + kundliResponse.getRashi());
            System.out.println("Nakshatra: " + kundliResponse.getNakshatra());
            System.out.println("Lagna: " + kundliResponse.getLagna());
            
            Map<String, Object> aiInsights = groqService.generateKundliInsights(
                kundliResponse.getRashi(),
                kundliResponse.getNakshatra(),
                kundliResponse.getLagna(),
                kundliResponse.getPlanets()
            );
            
            System.out.println("AI Insights generated successfully!");
            System.out.println("Insights: " + aiInsights);
            
            kundliResponse.setAiInsights(aiInsights);
        } catch (Exception e) {
            System.err.println("=== AI INSIGHTS GENERATION FAILED ===");
            e.printStackTrace();
        }
        
        return kundliResponse;
    }
}