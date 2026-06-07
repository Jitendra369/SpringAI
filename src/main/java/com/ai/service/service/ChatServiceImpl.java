package com.ai.service.service;

import com.ai.service.dto.ChatDto;
import com.ai.service.entity.TravelPlace;
import com.ai.service.entity.UserDetails;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatServiceImpl implements ChatService{

    private ChatClient chatClient;

    public ChatServiceImpl(ChatClient.Builder chatClient){
        this.chatClient = chatClient.build();
    }

    @Override
    public String chat(String query) {

        String prompt = "what is know about virat koli";
        Prompt prompt1 = new Prompt(prompt);

        String content = chatClient
                .prompt(prompt1)
                .call()
                .chatResponse()
                .getResult()
                .getOutput()
                .getText();

        return content;
    }

    // get the details using the person name and return the details in the form of UserDetails class
    @Override
    public UserDetails getUserDetails(String personName) {
        Prompt prompt = new Prompt("get the person details for the person name " + personName);
        UserDetails userResult = chatClient
                .prompt(prompt)
                .call()
                .entity(UserDetails.class);
        return userResult;
    }

    @Override
    public List<TravelPlace> getListOfPlacesToVisit(String place) {
        List<TravelPlace> travelPlaces = chatClient
                .prompt(new Prompt("get the list of places to visit in " + place))
                .call()
                .entity(new ParameterizedTypeReference<List<TravelPlace>>() {
                });

        return travelPlaces;
    }

    @Override
    public UserDetails getUserDetailsManageVariable(String personName) {
        // here we specify the maximum token and temp for the prompt
        Prompt prompt = new Prompt("get the person details for the person name " + personName, OpenAiChatOptions.builder()
                .model("")
                .temperature(0.3)
                .maxTokens(100)
                .build());

        return null;
    }

    @Override
    public String getResponseUsingPromptTemplate(ChatDto chatDto) {
        Prompt prompt = new Prompt(chatDto.getQuery());
        String query = "As an expert in coding and programming . Always write a program in java . now reply for this question {query}";

        String result = chatClient
                .prompt()
                .user(u -> u.text(query).param("query", chatDto.getQuery()))
                .call()
                .content();

        return result;
    }
}
