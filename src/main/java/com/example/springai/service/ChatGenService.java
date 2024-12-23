package com.example.springai.service;

import com.example.springai.model.ActorsFilms;
import com.example.springai.model.GoalRecordsByClub;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Map;
import java.util.Objects;

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

    public ChatResponse getAIChatResponse(String userInput){
        return chatClient.prompt().user(userInput).call().chatResponse();
    }

    public Flux<String> generateStreamingResponse(String userInput){
        return chatClient.prompt().user(userInput).stream().content();
    }

/*
TODO: Structured output not working. This is expected
"The StructuredOutputConverter is a best effort to convert the model output into a structured output.
The AI Model is not guaranteed to return the structured output as requested."


    //public GoalRecordsByClub getTopGoalScorersByClub(String club, String league, int limit) {
    public GoalRecordsByClub getTopGoalScorersByClub(String club) {
        //String input = String.format("Get top %d goal scorers for the club %s in the %s league", limit, club, league);
        BeanOutputConverter<GoalRecordsByClub> beanOutputConverter =
                new BeanOutputConverter<>(GoalRecordsByClub.class);

        String format = beanOutputConverter.getFormat();

//        return chatClient.prompt().user(u -> u.text("Get top {limit} goal scorers for the {club} in the {league}")
//                            .param("limit", 5)
//                            .param("club", "Liverpool")
//                            .param("league", "Premier league"))
//                            .call().entity(GoalRecordsByClub.class);

        String template = """
                "Get top 5 goal scorers for the {club} in the premier league"
                {format}
                """;

        Prompt prompt = new PromptTemplate(template, Map.of("club", club, "format", format)).create();
        ChatClient.CallResponseSpec responseSpec = chatClient.prompt(prompt).call();

        String content = responseSpec.content();
        System.out.println("----------------------");
        System.out.println(content);
        System.out.println("----------------------");
        return beanOutputConverter.convert(Objects.requireNonNull(content));

    }

 */

/*
TODO: Structured output not working. This is expected
"The StructuredOutputConverter is a best effort to convert the model output into a structured output.
The AI Model is not guaranteed to return the structured output as requested."


    public ActorsFilms getFilmsForAnActor(String actor){
        return chatClient.prompt()
                .user(u -> u.text("Generate the filmography of 5 movies for {actor}. Give just the names of the movies")
                        .param("actor", actor))
                .call()
                .entity(ActorsFilms.class);
    }

 */
}
