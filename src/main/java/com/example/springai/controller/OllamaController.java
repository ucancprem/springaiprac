package com.example.springai.controller;

import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OllamaController {

    private final OllamaChatModel ollamaChatModel;

    //@Autowired - can be omitted
    public OllamaController(OllamaChatModel ollamaChatModel){
        this.ollamaChatModel = ollamaChatModel;
    }

    @GetMapping("/ollama/generate")
    public String generateResponse(@RequestParam(value = "message", defaultValue = "Tell me a joke")  String message){
        return ollamaChatModel.call(message);
    }
}