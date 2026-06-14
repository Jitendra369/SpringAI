package com.ai.service.config;

import com.ai.service.advisor.TokenPrintAdvisor;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SafeGuardAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class AIConfig {


    private final TokenPrintAdvisor tokenPrintAdvisor;

    @Bean
    ChatClient chatClient(ChatClient.Builder builder) {
        return builder.
                defaultAdvisors(tokenPrintAdvisor, new SimpleLoggerAdvisor(), new SafeGuardAdvisor(List.of("game")))
                .build();
    }
}
