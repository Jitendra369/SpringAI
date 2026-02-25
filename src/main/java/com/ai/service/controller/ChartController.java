package com.ai.service.controller;

import com.ai.service.dto.ChatDto;
import org.slf4j.Logger;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;


@RestController
@RequestMapping("/api/chat")
public class ChartController {


    private ChatClient chatClient;

    Logger logger = org.slf4j.LoggerFactory.getLogger(ChartController.class);

    public ChartController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @PostMapping
    public ResponseEntity<String> chat(@RequestBody ChatDto chatDto){
        double startTIme = System.currentTimeMillis();
        logger.info("fire chat query: {} and time  {} ", chatDto.getQuery(), LocalTime.now());
        String response = chatClient.prompt(chatDto.getQuery()).call().content();
        double endTime = System.currentTimeMillis();

        logger.info("total time for chat time in sec : {} ", (endTime - startTIme)/1000);
        return ResponseEntity.ok(response);
    }
}
