package com.example.springai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatGenService {

    private final ChatClient chatClient;

    @Autowired
    public ChatGenService(ChatClient.Builder chatClientBuilder){
        this.chatClient = chatClientBuilder.build();
    }

    public String generateAIResponse(String userInput) {
        System.out.println(chatClient.getClass().getCanonicalName());
        return chatClient.prompt().user(userInput).call().content();
    }
}
