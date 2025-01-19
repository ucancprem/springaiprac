package com.example.springai.service;

import com.example.springai.util.LoggingAdvisorUtil;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatGenServiceWithCustomLogs {

    private final ChatClient chatClient;

    @Autowired
    public ChatGenServiceWithCustomLogs(ChatClient.Builder chatClientBuilder){
        this.chatClient = chatClientBuilder.build();
    }

    public String generateResponseWithCustomLogs(String userQuestion){
        return chatClient
                .prompt()
                .advisors(LoggingAdvisorUtil.getCustomLoggingAdvisor())
                .user(userQuestion)
                .call()
                .content();

    }
}
