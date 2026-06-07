package com.ai.service.controller;

import com.ai.service.dto.ChatDto;
import com.ai.service.entity.UserDetails;
import com.ai.service.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/openAI")
public class OpenAiChatController {

    private final ChatClient chatClient;
    private final ChatService chatService;

    public OpenAiChatController(ChatClient.Builder chatClientBuilder, ChatService chatService){
        this.chatClient = chatClientBuilder.build();
        this.chatService = chatService;
    }

    @PostMapping("/chat")
    public ResponseEntity<String> chat(@RequestBody ChatDto chatDto){
        String chat = chatService.chat(chatDto.getQuery());
        return ResponseEntity.ok(chat);
    }

    @PostMapping("/userDetails")
    public ResponseEntity<UserDetails> getUserDetails(@RequestBody ChatDto chatDto){
        UserDetails userDetails = chatService.getUserDetails(chatDto.getQuery());
        return ResponseEntity.ok(userDetails);
    }
}
