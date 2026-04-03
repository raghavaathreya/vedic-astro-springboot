package com.example.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.*;

@Service
public class GroqService {

    @Value("${groq.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public Map<String, Object> generateKundliInsights(
        String rashi, 
        String nakshatra, 
        String lagna,
        Map<String, Object> planets
    ) {
        String prompt = buildPrompt(rashi, nakshatra, lagna, planets);
        String response = callGroq(prompt);
        return parseResponse(response);
    }

    private String buildPrompt(String rashi, String nakshatra, String lagna, Map<String, Object> planets) {
        StringBuilder prompt = new StringBuilder();
        prompt.append("You are an expert Vedic astrologer. Analyze this birth chart:\n\n");
        prompt.append("Rashi: ").append(rashi).append("\n");
        prompt.append("Nakshatra: ").append(nakshatra).append("\n");
        prompt.append("Lagna: ").append(lagna).append("\n");
        prompt.append("Planets:\n");
        
        planets.forEach((planet, data) -> {
            Map<String, Object> planetData = (Map<String, Object>) data;
            prompt.append("- ").append(planet).append(": House ")
                  .append(planetData.get("house")).append(", ")
                  .append(planetData.get("rashi")).append("\n");
        });
        
        prompt.append("\nReturn ONLY this JSON (no markdown):\n");
        prompt.append("{\"personality\":[\"trait1\",\"trait2\",\"trait3\",\"trait4\"],");
        prompt.append("\"strengths\":[\"s1\",\"s2\",\"s3\"],");
        prompt.append("\"challenges\":[\"c1\",\"c2\",\"c3\"],");
        prompt.append("\"careerGuidance\":\"text\",");
        prompt.append("\"remedies\":{\"primary\":{\"planet\":\"name\",\"recommendations\":[\"r1\",\"r2\",\"r3\"]},");
        prompt.append("\"secondary\":{\"planet\":\"name\",\"recommendations\":[\"r1\",\"r2\",\"r3\"]}}}");
        
        return prompt.toString();
    }

    private String callGroq(String prompt) {
        String url = "https://api.groq.com/openai/v1/chat/completions";
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey);
        
        Map<String, Object> message = new HashMap<>();
        message.put("role", "user");
        message.put("content", prompt);
        
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "llama-3.3-70b-versatile");
        requestBody.put("messages", List.of(message));
        requestBody.put("temperature", 0.7);
        requestBody.put("max_tokens", 1000);
        
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);
        
        try {
            ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.POST, request, Map.class);
            Map<String, Object> responseBody = response.getBody();
            List<Map<String, Object>> choices = (List<Map<String, Object>>) responseBody.get("choices");
            Map<String, Object> firstChoice = choices.get(0);
            Map<String, String> message2 = (Map<String, String>) firstChoice.get("message");
            return message2.get("content");
        } catch (Exception e) {
            System.err.println("Groq API Error: " + e.getMessage());
            return getFallbackResponse();
        }
    }

    private Map<String, Object> parseResponse(String response) {
        try {
            String cleaned = response.trim()
                .replaceAll("```json", "")
                .replaceAll("```", "")
                .trim();
            
            com.fasterxml.jackson.databind.ObjectMapper mapper = 
                new com.fasterxml.jackson.databind.ObjectMapper();
            return mapper.readValue(cleaned, Map.class);
        } catch (Exception e) {
            return parseFallbackResponse();
        }
    }

    private String getFallbackResponse() {
        return "{\"personality\":[\"Intuitive\",\"Analytical\",\"Creative\",\"Determined\"]," +
               "\"strengths\":[\"Natural leadership\",\"Strong communication\",\"Problem-solving\"]," +
               "\"challenges\":[\"Impulsiveness\",\"Overthinking\",\"Impatience\"]," +
               "\"careerGuidance\":\"Success in creative and analytical fields. Technology, business, or arts.\"," +
               "\"remedies\":{\"primary\":{\"planet\":\"Mars\",\"recommendations\":[\"Donate red lentils on Tuesdays\",\"Chant Hanuman Chalisa\",\"Wear red coral\"]}," +
               "\"secondary\":{\"planet\":\"Moon\",\"recommendations\":[\"Drink water from silver glass\",\"Avoid cold foods\",\"Meditate before sleep\"]}}}";
    }

    private Map<String, Object> parseFallbackResponse() {
        try {
            com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
            return mapper.readValue(getFallbackResponse(), Map.class);
        } catch (Exception e) {
            return new HashMap<>();
        }
    }
}