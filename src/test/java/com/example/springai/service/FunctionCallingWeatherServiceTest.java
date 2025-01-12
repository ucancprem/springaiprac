package com.example.springai.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/*
* Smaller models do not support function calling.
* {"error":"registry.ollama.ai/library/tinydolphin:latest does not support tools"}
*/
@Disabled
@SpringBootTest
class FunctionCallingWeatherServiceTest {

    @Autowired
    private FunctionCallingWeatherService functionCallingWeatherService;

    @Test
    public void testFunctionCalling(){
        String response = functionCallingWeatherService.getCurrentWeather("What is the current weather in Burlington, Vermont?");
        System.out.println(response);
        assertTrue(response.contains("80"));
    }
}