package com.example.springai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.model.function.FunctionCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FunctionCallingWeatherService {

    private final ChatClient chatClient;

    @Autowired
    public FunctionCallingWeatherService(ChatClient.Builder chatClientBuilder) {
        this.chatClient =  chatClientBuilder.build();
    }

    public String getCurrentWeather(String userText){
        return chatClient.prompt()
                .user(userText)
                .functions("mockWeatherService")
//                .functions(FunctionCallback.builder()
//                        .description("Get the current weather in location")
//                        .function("currentWeather", new MockWeatherService())
//                        .inputType(MockWeatherService.Request.class)
//                        .build())
                .call()
                .content();
    }
}
