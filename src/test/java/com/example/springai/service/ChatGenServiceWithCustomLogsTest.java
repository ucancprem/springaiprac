package com.example.springai.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ChatGenServiceWithCustomLogsTest {

    @Autowired
    private ChatGenServiceWithCustomLogs chatGenServiceWithCustomLogs;

    @Test
    public void testLogsOnCallToModel(){
        String response = chatGenServiceWithCustomLogs.generateResponseWithCustomLogs("What is the best country in the world");

    }
}