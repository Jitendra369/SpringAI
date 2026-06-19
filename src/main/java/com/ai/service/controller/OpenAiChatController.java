package com.ai.service.controller;

import com.ai.service.dto.ChatDto;
import com.ai.service.ChatAiResponseEntity.TravelPlace;
import com.ai.service.ChatAiResponseEntity.UserDetails;
import com.ai.service.entity.AIResponse;
import com.ai.service.service.ChatService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/openAI")
public class OpenAiChatController {

    private final ChatClient chatClient;
    private final ChatService chatService;

    public OpenAiChatController(ChatClient.Builder chatClientBuilder, ChatService chatService) {
        this.chatClient = chatClientBuilder.build();
        this.chatService = chatService;
    }

    @PostMapping("/chat/basic")
    public ResponseEntity<String> chat(@RequestBody ChatDto chatDto) {
        String chat = chatService.chat(chatDto.getQuery());
        return ResponseEntity.ok(chat);
    }

    @PostMapping("/userDetails")
    public ResponseEntity<UserDetails> getUserDetails(@RequestBody ChatDto chatDto) {
        UserDetails userDetails = chatService.getUserDetails(chatDto.getQuery());
        return ResponseEntity.ok(userDetails);
    }

    @PostMapping("/travel")
    public ResponseEntity<List<TravelPlace>> fetchTravelPlacesToVisit(@RequestBody ChatDto chatDto) {
        List<TravelPlace> travelPlace = chatService.getListOfPlacesToVisit(chatDto.getQuery());
        return ResponseEntity.ok(travelPlace);
    }

    @PostMapping("/chat/prompt")
    public ResponseEntity<String> getResultUsingCustomPrompt(@RequestBody ChatDto chatDto, @RequestHeader("userId") String userId ) {
        String result = chatService.getResponseUsingPromptTemplate(chatDto, userId);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/chat/prompt/{topic}")
    public ResponseEntity<String> getResultUsingCustomPrompt(@PathVariable String topic) {
        String result = chatService.getResponseUsePromptTemplate(topic);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/chat/viewAllResponse")
    public List<AIResponse> viewAllGeneratedResponse() {
        return chatService.viewAllResponse();
    }
}
