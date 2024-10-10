package com.tlijani.sifo_ai;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
public class ChatController {


    private final ChatClient chatClient;


    public ChatController(ChatClient.Builder Builder) {
        this.chatClient = Builder.build();
    }


    @PostMapping("/chat")
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> generate(@RequestParam(value = "message", required = false) String message) {
        String response = chatClient.prompt()
                .user(message)
                .call()
                .content();

        return ResponseEntity.ok("<div class='chat-message'>" + response + "</div>");
    }



    @GetMapping("/stream")
     public Flux<String> chatWithModel(@RequestParam String message){
        return chatClient.prompt()
                .user(message)
                .stream().content();
    }




}
