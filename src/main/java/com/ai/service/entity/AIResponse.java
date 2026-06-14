package com.ai.service.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "ai_response")
public class AIResponse {

    @Id
    private String id;
    private String questionAsked;
    private String chatResponse;
    private int inputTokenUsed;
    private int outputTokenUsed;
    private int totalTokenUsed;
}
