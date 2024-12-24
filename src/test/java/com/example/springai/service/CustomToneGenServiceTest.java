package com.example.springai.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomToneGenServiceTest {

    @Autowired
    CustomToneGenService customToneGenService;

    @Test
    public void generateResponseInToneOfANutritionist(){
        String response = customToneGenService.generateResponse("Diet and Nutrition",
                "What is the best diet for an athlete before a game");

        System.out.println(response);
        assertTrue(response.contains("protein"));
        assertTrue(response.contains("carb"));
    }

    @Test
    public void generateResponseInToneOfACarSalesGuy(){
        String response = customToneGenService.generateResponse("Car Sales",
                "Provide a sales pitch for hybrid vehicle and why they have to buy from your dealership");

        System.out.println(response);
        assertTrue(response.contains("dealership"));
        assertTrue(response.contains("hybrid"));
    }

}