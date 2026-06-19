package com.ai.service.service;

import com.ai.service.ChatAiResponseEntity.TravelPlace;
import com.ai.service.ChatAiResponseEntity.UserDetails;
import com.ai.service.dto.ChatDto;
import com.ai.service.entity.AIResponse;
import com.ai.service.repo.AIResponseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService{
    private final AIResponseRepository aIResponseRepository;

    private final ChatClient chatClient;
    private final AIResponseService aiResponseService;

    @Value("classpath:/prompts/user-message.st")
    private Resource userMessage;

//    public ChatServiceImpl(ChatClient.Builder chatClient){
//        this.chatClient = chatClient.build();
//    }

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
    public String getResponseUsingPromptTemplate(ChatDto chatDto, String userId ) {
        Prompt prompt = new Prompt(chatDto.getQuery());
        String query = "As an expert in coding and programming . Always write a program in java . now reply for this question {query}";

        String result = chatClient
                .prompt()
                .advisors(advisorSpec -> advisorSpec.param(ChatMemory.CONVERSATION_ID, userId))
                .user(u -> u.text(query).param("query", chatDto.getQuery()))
                .call()
                .content();

        return result;
    }

    @Override
    public String getResponseUsePromptTemplate(String topic) {
        Prompt prompt = new Prompt(topic);
        String query = "As an expert in coding and programming . Always write a program in java . now reply for this question {query}";

        ChatResponse chatResponse = chatClient
                .prompt()
                .user(u -> u.text(query).param("query", topic))
                .call()
                .chatResponse();

        if (chatResponse != null){
            logChatResponse(chatResponse, topic);
        }else{
            log.info("No response From LLM ");
        }
        return chatResponse.getResult().getOutput().getText();
    }



    @Override
    public String getResultFormResourceFile() {
        return "";
    }

    @Override
    public List<AIResponse> viewAllResponse() {
        return aIResponseRepository.findAll();
    }

    private void logChatResponse(ChatResponse chatResponse, String userInput ) {
        AIResponse aiResponse = AIResponse.builder()
                .outputTokenUsed(chatResponse.getMetadata().getUsage().getCompletionTokens())
                .inputTokenUsed(chatResponse.getMetadata().getUsage().getPromptTokens())
                .questionAsked(userInput)
                .totalTokenUsed(chatResponse.getMetadata().getUsage().getTotalTokens())
                .chatResponse(chatResponse.getResult().getOutput().getText())
                .build();

        aiResponseService.saveAiResponse(aiResponse);
    }
}
