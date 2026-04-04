package com.example.demo.controller;

import com.example.demo.model.BirthDetails;
import com.example.demo.model.KundliResponse;
import com.example.demo.service.AstrologyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/kundli")
@CrossOrigin(origins = "*")
public class AstrologyController {

    @Autowired
    private AstrologyService astrologyService;

    @PostMapping("/calculate")
    public KundliResponse calculateKundli(@RequestBody BirthDetails birthDetails) {
        System.out.println("=== RECEIVED KUNDLI REQUEST ===");
        System.out.println("Date: " + birthDetails.getYear() + "/" + birthDetails.getMonth() + "/" + birthDetails.getDay());
        System.out.println("Time: " + birthDetails.getHour() + ":" + birthDetails.getMinute());
        System.out.println("Location: " + birthDetails.getLatitude() + ", " + birthDetails.getLongitude());
        
        return astrologyService.calculateKundli(
            birthDetails.getYear(),
            birthDetails.getMonth(),
            birthDetails.getDay(),
            birthDetails.getHour(),
            birthDetails.getMinute(),
            birthDetails.getLatitude(),
            birthDetails.getLongitude()
        );
    }
    
    @GetMapping("/health")
    public String health() {
        return "Kundli API is running!";
    }
}