package com.ai.service.service;


import com.ai.service.entity.AIResponse;
import com.ai.service.repo.AIResponseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AIResponseService {

    private final AIResponseRepository aiResponseRepository;

    public AIResponse saveAiResponse(AIResponse aiResponse) {
        return aiResponseRepository.save(aiResponse);
    }

    public List<AIResponse> viewAllAiResponse() {
        return aiResponseRepository.findAll();
    }
}
