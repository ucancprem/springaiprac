package com.example.springai.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * orca-mini model i use does not give you the name even when you have a MessageChatMemoryAdvisor
 * The answer we get from orca is
 * """
 * I'm sorry, I don't have access to any information about a specific person named "Bob". Could you please ...
 * """
 */
@Disabled
@SpringBootTest
class ChatMemoryGenServiceTest {

    @Autowired
    private ChatMemoryGenService chatMemoryGenService;


    private static void printResponseInReadableFormat(String initialResponse) {
        System.out.println(" ");
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println(initialResponse);
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println(" ");
    }

    @Test
    public void chatResponseWithChatMemory(){
        String initialResponse = chatMemoryGenService.generateResponseWithChatMemory("Hi, My name is Bob. " +
                "Can you remember name for further questions.");
        printResponseInReadableFormat(initialResponse);
        String followUpResponse = chatMemoryGenService.generateResponseWithChatMemory("What is my name?");
        printResponseInReadableFormat(followUpResponse);

        //assertTrue(initialResponse.contains("Bob"));
        assertTrue(followUpResponse.contains("Bob"), "Chat service now remembers the name/context " +
                "with the chatMemory advisor");

    }

}