package com.tlijani.sifo_ai;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.retry.NonTransientAiException;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.Map;

@RestController
public class ChatController {


    private final ChatClient chatClient;


    public ChatController(ChatClient.Builder Builder) {
        this.chatClient = Builder.build();
    }

    @GetMapping("/chat")
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String generate(@RequestParam(value = "message", required = false) String message) {
        return chatClient.prompt()
                .user(message)
                .call()
                .content();
    }


    @GetMapping("/jokes")
     public Flux<String> chatWithModel(@RequestParam String message){
        return chatClient.prompt()
                .user(message)
                .stream().content();
    }


}
