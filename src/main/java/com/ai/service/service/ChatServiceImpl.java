package com.ai.service.service;

import com.ai.service.entity.UserDetails;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;

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
}
