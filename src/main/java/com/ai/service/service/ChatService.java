package com.ai.service.service;

import com.ai.service.entity.UserDetails;

public interface ChatService {
    String chat(String query);
    UserDetails getUserDetails(String personName);

}
