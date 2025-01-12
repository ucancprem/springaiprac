package com.example.springai.service;

import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@Description("Get the current weather in location")
public class MockWeatherService implements Function<MockWeatherService.Request, MockWeatherService.Response> {

    public enum Unit {C , F};
    public record Request(String location, Unit unit){}
    public record Response(double temp, Unit unit){}

    @Override
    public Response apply(Request request) {
        System.out.println("MockWeatherService returns 80F");
        return new Response(80, Unit.F);
    }
}
