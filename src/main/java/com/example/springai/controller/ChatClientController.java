package com.example.springai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatClientController {

    private ChatClient chatClient;

    public ChatClientController(ChatClient.Builder chatClientBuilder){
        chatClient =  chatClientBuilder.build();
    }

    @GetMapping("/chat-client/generate")
    public String generateResponse(@RequestParam(value = "userInput", defaultValue = "Tell me a joke") String userInput){
        System.out.println(chatClient.getClass().getCanonicalName());
        return chatClient.prompt().user(userInput).call().content();
    }
}
