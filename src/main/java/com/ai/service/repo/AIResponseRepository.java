package com.ai.service.repo;

import com.ai.service.entity.AIResponse;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AIResponseRepository extends MongoRepository<AIResponse, String> {
}
