package com.example.springai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomToneGenService {

    private final ChatClient chatClient;

    private static final String EXPERTISE = "expertise";

    @Autowired
    public CustomToneGenService(ChatClient.Builder chatClientBuilder) {
        String defaultSystemTone = """
                You are an expert in the field of {%s} that answers questions in a polite manner with details about your answer
                """.formatted(EXPERTISE);

        this.chatClient = chatClientBuilder
                .defaultSystem(defaultSystemTone)
                .build();
    }

    public String generateResponse(String expertise, String userQuestion){
        return chatClient.prompt().system(systemParam -> systemParam.param(EXPERTISE, expertise))
                .user(userQuestion)
                .call()
                .content();
    }
}
