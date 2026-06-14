package com.ai.service.advisor;

import com.ai.service.entity.AIResponse;
import com.ai.service.service.AIResponseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClientRequest;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.client.advisor.api.CallAdvisor;
import org.springframework.ai.chat.client.advisor.api.CallAdvisorChain;
import org.springframework.ai.chat.client.advisor.api.StreamAdvisor;
import org.springframework.ai.chat.client.advisor.api.StreamAdvisorChain;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Slf4j
@RequiredArgsConstructor
@Component
public class TokenPrintAdvisor implements CallAdvisor, StreamAdvisor {

//    private final AIResponseService aiResponseService;

    @Override
    public ChatClientResponse adviseCall(ChatClientRequest chatClientRequest, CallAdvisorChain callAdvisorChain) {

        log.info("TOkenPrintAdvisor call");
        log.info("request " + chatClientRequest.prompt().getContents());
        ChatClientResponse chatClientResponse = callAdvisorChain.nextCall(chatClientRequest);
        log.info("response " + chatClientResponse.chatResponse().getResult().getOutput().getText());
        log.info("user-token [ input-token ] " + chatClientResponse.chatResponse().getMetadata().getUsage().getPromptTokens());
        log.info("user-token [output-token ] " + chatClientResponse.chatResponse().getMetadata().getUsage().getCompletionTokens());
        log.info("total token consume " + chatClientResponse.chatResponse().getMetadata().getUsage().getTotalTokens());

//        AIResponse aiResponse = AIResponse.builder()
//                .questionAsked(chatClientRequest.prompt().getContents())
//                .chatResponse(chatClientResponse.chatResponse().getResult().getOutput().getText())
//                .inputTokenUsed(chatClientResponse.chatResponse().getMetadata().getUsage().getPromptTokens())
//                .outputTokenUsed(chatClientResponse.chatResponse().getMetadata().getUsage().getCompletionTokens())
//                .build();
//
//        aiResponseService.saveAiResponse(aiResponse);

        return chatClientResponse;
    }

    @Override
    public Flux<ChatClientResponse> adviseStream(ChatClientRequest chatClientRequest, StreamAdvisorChain streamAdvisorChain) {
        return null;
    }

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
