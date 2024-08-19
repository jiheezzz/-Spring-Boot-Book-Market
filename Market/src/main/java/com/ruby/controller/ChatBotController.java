package com.ruby.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rubypaper.chatbot.ChatGPTService;


@RestController
public class ChatBotController {

    private final ChatGPTService chatGPTService;

    public ChatBotController(ChatGPTService chatGPTService) {
        this.chatGPTService = chatGPTService;
    }

    @GetMapping("/recommend")
    public String recommendBooks(@RequestParam String query) {
        return chatGPTService.getBookRecommendations(query);
    }
    

}