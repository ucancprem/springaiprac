package com.example.springai.service;

import com.example.springai.model.ActorsFilms;
import com.example.springai.model.GoalRecordsByClub;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        assertTrue(response.contains("scattering"));//TODO: use evaluators
    }


    @Test
    void getAIChatResponse() {
        ChatResponse chatResponse  = chatGenService.getAIChatResponse("Why is the sky blue?");
        assertNotNull(chatResponse);
        System.out.println(chatResponse);
        assertTrue(chatResponse.getResult().getOutput().getContent().contains("scattering"));
        assertTrue(chatResponse.getResults().get(0).getOutput().getContent().contains("scattering"));

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
