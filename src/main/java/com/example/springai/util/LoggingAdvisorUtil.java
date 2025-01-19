package com.example.springai.util;

import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.client.advisor.api.AdvisedRequest;
import org.springframework.ai.chat.metadata.PromptMetadata;
import org.springframework.ai.chat.model.ChatResponse;

public class LoggingAdvisorUtil {


    private static String logRequestSentToModel(AdvisedRequest advisedRequest){
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

    private static String logResponseFromModel(ChatResponse chatResponse){
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

    public static SimpleLoggerAdvisor getCustomLoggingAdvisor(){
        return new SimpleLoggerAdvisor(
                advisedRequest -> logRequestSentToModel(advisedRequest),
                chatResponse -> logResponseFromModel(chatResponse),
                0
        );
    }
}
