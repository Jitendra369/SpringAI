package com.ai.service.service;

import com.ai.service.dto.ChatDto;
import com.ai.service.ChatAiResponseEntity.TravelPlace;
import com.ai.service.ChatAiResponseEntity.UserDetails;
import com.ai.service.entity.AIResponse;

import java.util.List;

public interface ChatService {
    String chat(String query);
    UserDetails getUserDetails(String personName);
    List<TravelPlace> getListOfPlacesToVisit(String place);
    UserDetails getUserDetailsManageVariable(String personName);
    String getResponseUsingPromptTemplate(ChatDto chatDto, String userId );
    String getResponseUsePromptTemplate(String topic);
    String getResultFormResourceFile();
    List<AIResponse> viewAllResponse();


}
