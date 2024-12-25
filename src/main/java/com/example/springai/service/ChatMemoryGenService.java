package com.example.springai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatMemoryGenService {

    private final ChatClient chatClientWithMemory;

    @Autowired
    public ChatMemoryGenService(ChatClient.Builder chatClientBuilder){
        this.chatClientWithMemory = chatClientBuilder.defaultAdvisors(new MessageChatMemoryAdvisor(new InMemoryChatMemory()))
                                .build();
    }

    public String generateResponseWithChatMemory(String userQuestion){
        return  chatClientWithMemory.prompt()
                //.advisors(new MessageChatMemoryAdvisor(new InMemoryChatMemory()))
                .user(userQuestion)
                .call()
                .content();
    }
}
