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
}