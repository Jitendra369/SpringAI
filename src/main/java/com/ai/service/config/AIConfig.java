package com.ai.service.config;

import com.ai.service.advisor.TokenPrintAdvisor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SafeGuardAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class AIConfig {


    private final TokenPrintAdvisor tokenPrintAdvisor;

    @Bean
    ChatClient chatClient(ChatClient.Builder builder, ChatMemory chatMemory) {

        log.info("chatMemory Impl class " + chatMemory.getClass().getName());
        MessageChatMemoryAdvisor messageChatMemoryAdvisor = MessageChatMemoryAdvisor.builder(chatMemory).build();

        return builder.
                defaultAdvisors(tokenPrintAdvisor, messageChatMemoryAdvisor, new SimpleLoggerAdvisor(), new SafeGuardAdvisor(List.of("game")))
                .build();
    }
}
