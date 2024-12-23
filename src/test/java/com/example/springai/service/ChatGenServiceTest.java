package com.example.springai.service;

import com.example.springai.model.ActorsFilms;
import com.example.springai.model.GoalRecordsByClub;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ChatGenServiceTest {

    @Autowired
    private ChatGenService chatGenService;

    @Test
    void generateAIResponse(){
        String response = chatGenService.generateAIResponse("Why is the sky blue?");
        assertNotNull(response);
        System.out.println(response);
        assertTrue(response.contains("scattering"));//TODO: check evaluators
    }


    @Test
    void getAIChatResponse() {
        ChatResponse chatResponse  = chatGenService.getAIChatResponse("Why is the sky blue?");
        assertNotNull(chatResponse);
        System.out.println(chatResponse);
        assertTrue(chatResponse.getResult().getOutput().getContent().contains("scattering"));
        assertTrue(chatResponse.getResults().get(0).getOutput().getContent().contains("scattering"));//TODO: check evaluators

    }

    @Test
    void generateStreamingResponse() {
        Flux<String> streamingResponse = chatGenService.generateStreamingResponse("Why is the sky blue?");

        StringBuilder builder = new StringBuilder();
        long startTime = System.currentTimeMillis();
        long timeTaken  =  0;
//        streamingResponse.subscribe(
//                data -> {
//                    builder.append(data);
//                    System.out.println("Data:  "+ data);
//                },
//                error -> System.out.println("Error : "+error),
//                 () -> {
//
//                    System.out.println("Stream completed");
//                 }
//        );

        streamingResponse
                .doOnNext(data -> {
                    builder.append(data);
                    System.out.println("Data:  "+ data);
                })
                .doOnError(error -> System.err.println("Error : "+error))
                .doOnComplete(() -> System.out.println("Stream completed"))
                .blockLast(Duration.ofMinutes(2));

        timeTaken = (System.currentTimeMillis() - startTime) / 1000;
        String fullResponse = builder.toString();
        System.out.println(fullResponse+" takes "+timeTaken+ " seconds");
        assertNotNull(fullResponse);
        assertTrue(timeTaken > 5 , "Time taken for response is more than 0 secs");
        assertTrue(fullResponse.contains("scattering"), "Response contains keyword scattering");//TODO: check evaluators
    }

/*
    @Test
    void getTopGoalScorersByClub() {
        int numOfGoalScorers = 5;
        GoalRecordsByClub goalRecordsByClub = chatGenService.getTopGoalScorersByClub("Liverpool");
        assertNotNull(goalRecordsByClub);
        assertEquals(goalRecordsByClub.goalScorers().size(), numOfGoalScorers);
        System.out.println(goalRecordsByClub.goalScorers());
    }
*/


/*
    @Test
    void getTop5FilmsOfAnActor(){
        ActorsFilms actorsFilms = chatGenService.getFilmsForAnActor("Tom Hanks");
        assertNotNull(actorsFilms);
        System.out.println(actorsFilms);
    }
*/

}
