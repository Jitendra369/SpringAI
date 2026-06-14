package com.ai.service;

import com.ai.service.service.ChatService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AiServiceApplicationTests {

	@Autowired
	private ChatService chatService;


	@Test
	void contextLoads() {
		String resultFormResourceFile = chatService.getResultFormResourceFile();
		System.out.println(resultFormResourceFile);
	}

}
