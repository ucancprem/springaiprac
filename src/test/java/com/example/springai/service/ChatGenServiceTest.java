package com.example.springai.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ChatGenServiceTest {

    @Autowired
    private ChatGenService chatGenService;

    @Test
    void generateAIResponse(){
        String response = chatGenService.generateAIResponse("Why is the sky blue?");
        assertNotNull(response);
        System.out.println(response);
        assertTrue(response.contains("scattering"));//TODO: use evaluators
    }
}
