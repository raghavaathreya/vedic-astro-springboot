package com.example.demo.model;

import java.util.Map;
import java.util.List;

public class KundliResponse {
    private boolean success;
    private String rashi;
    private String nakshatra;
    private String lagna;
    private Map<String, Object> planets;
    private Map<String, String> birthDetails;
    private Double ascendantDegree;
    private String houseSystem;
    private Map<String, Object> aiInsights;
    private Map<String, Object> nakshatraDetails;
    private Map<String, Object> rashiDetails;
    private Map<String, Object> dailyPredictions;
    private Map<String, Object> horoscopeSummary;
    
    // NEW FIELDS - Add these
    private Map<String, Object> currentTransits;
    private List<Map<String, Object>> transitInsights;
    private List<Map<String, Object>> mahadashas;
    private Map<String, Object> currentDasha;

    // Getters and Setters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getRashi() {
        return rashi;
    }

    public void setRashi(String rashi) {
        this.rashi = rashi;
    }

    public String getNakshatra() {
        return nakshatra;
    }

    public void setNakshatra(String nakshatra) {
        this.nakshatra = nakshatra;
    }

    public String getLagna() {
        return lagna;
    }

    public void setLagna(String lagna) {
        this.lagna = lagna;
    }

    public Map<String, Object> getPlanets() {
        return planets;
    }

    public void setPlanets(Map<String, Object> planets) {
        this.planets = planets;
    }

    public Map<String, String> getBirthDetails() {
        return birthDetails;
    }

    public void setBirthDetails(Map<String, String> birthDetails) {
        this.birthDetails = birthDetails;
    }

    public Double getAscendantDegree() {
        return ascendantDegree;
    }

    public void setAscendantDegree(Double ascendantDegree) {
        this.ascendantDegree = ascendantDegree;
    }

    public String getHouseSystem() {
        return houseSystem;
    }

    public void setHouseSystem(String houseSystem) {
        this.houseSystem = houseSystem;
    }

    public Map<String, Object> getAiInsights() {
        return aiInsights;
    }

    public void setAiInsights(Map<String, Object> aiInsights) {
        this.aiInsights = aiInsights;
    }

    public Map<String, Object> getNakshatraDetails() {
        return nakshatraDetails;
    }

    public void setNakshatraDetails(Map<String, Object> nakshatraDetails) {
        this.nakshatraDetails = nakshatraDetails;
    }

    public Map<String, Object> getRashiDetails() {
        return rashiDetails;
    }

    public void setRashiDetails(Map<String, Object> rashiDetails) {
        this.rashiDetails = rashiDetails;
    }

    // NEW GETTERS/SETTERS
    public Map<String, Object> getCurrentTransits() {
        return currentTransits;
    }

    public void setCurrentTransits(Map<String, Object> currentTransits) {
        this.currentTransits = currentTransits;
    }

    public List<Map<String, Object>> getTransitInsights() {
        return transitInsights;
    }

    public void setTransitInsights(List<Map<String, Object>> transitInsights) {
        this.transitInsights = transitInsights;
    }

    public List<Map<String, Object>> getMahadashas() {
        return mahadashas;
    }

    public void setMahadashas(List<Map<String, Object>> mahadashas) {
        this.mahadashas = mahadashas;
    }

    public Map<String, Object> getCurrentDasha() {
        return currentDasha;
    }

    public void setCurrentDasha(Map<String, Object> currentDasha) {
        this.currentDasha = currentDasha;
    }
        public Map<String, Object> getDailyPredictions() {
        return dailyPredictions;
    }

    public void setDailyPredictions(Map<String, Object> dailyPredictions) {
        this.dailyPredictions = dailyPredictions;
    }

    public Map<String, Object> getHoroscopeSummary() {
        return horoscopeSummary;
    }

    public void setHoroscopeSummary(Map<String, Object> horoscopeSummary) {
        this.horoscopeSummary = horoscopeSummary;
    }
}