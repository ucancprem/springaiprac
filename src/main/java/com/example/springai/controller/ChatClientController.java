package com.example.springai.controller;

import com.example.springai.service.ChatGenService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatClientController {

    private final ChatGenService chatGenService;

    public ChatClientController(ChatClient.Builder chatClientBuilder, ChatGenService chatGenService){
        this.chatGenService = chatGenService;
    }

    @GetMapping("/chat-client/generate")
    public String generateResponse(@RequestParam(value = "userInput", defaultValue = "Tell me a joke") String userInput){
        return chatGenService.generateAIResponse(userInput);
    }


}
