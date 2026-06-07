package com.ai.service.service;

import com.ai.service.dto.ChatDto;
import com.ai.service.entity.TravelPlace;
import com.ai.service.entity.UserDetails;

import java.util.List;

public interface ChatService {
    String chat(String query);
    UserDetails getUserDetails(String personName);
    List<TravelPlace> getListOfPlacesToVisit(String place);
    UserDetails getUserDetailsManageVariable(String personName);
    String getResponseUsingPromptTemplate(ChatDto chatDto);

}
