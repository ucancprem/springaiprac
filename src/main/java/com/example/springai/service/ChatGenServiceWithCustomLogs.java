package com.example.springai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.client.advisor.api.AdvisedRequest;
import org.springframework.ai.chat.metadata.PromptMetadata;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatGenServiceWithCustomLogs {

    private final ChatClient chatClient;

    @Autowired
    public ChatGenServiceWithCustomLogs(ChatClient.Builder chatClientBuilder){
        this.chatClient = chatClientBuilder.build();
    }

    private String logRequestSentToModel(AdvisedRequest advisedRequest){
        return """
               \s
               ----------------------------------------------------------------------------------------------------
               Calling chat model - (%s),
               \s
               with user text - (%s)
               \s
               and with context - (%s)
               ----------------------------------------------------------------------------------------------------
              \s""".formatted(advisedRequest.chatModel(), advisedRequest.userText(), advisedRequest.adviseContext());
    }

    private static String getPromptMetadataInfo(ChatResponse chatResponse) {
        PromptMetadata promptMetadata = chatResponse.getMetadata().getPromptMetadata();
        promptMetadata.forEach(promptFilterMetadata -> promptMetadata.getClass());
        return "";
    }

    private String logResponseFromModel(ChatResponse chatResponse){
        return """
               \s
               ----------------------------------------------------------------------------------------------------
                Response (%s)
                \s
                prompt metadata (%s)
                \s
                Response metadata (%s)
               ----------------------------------------------------------------------------------------------------
                \s""".formatted(chatResponse.getResult().toString(), chatResponse.getResult(),
                chatResponse.getMetadata());
    }

    private SimpleLoggerAdvisor getCustomLoggingAdvisor(){
        return new SimpleLoggerAdvisor(
                advisedRequest -> logRequestSentToModel(advisedRequest),
                chatResponse -> logResponseFromModel(chatResponse),
                0
        );
    }

    public String generateResponseWithCustomLogs(String userQuestion){
        return chatClient
                .prompt()
                .advisors(getCustomLoggingAdvisor())
                .user(userQuestion)
                .call()
                .content();

    }
}
