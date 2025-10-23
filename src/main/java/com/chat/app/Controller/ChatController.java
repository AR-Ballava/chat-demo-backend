package com.chat.app.Controller;

import com.chat.app.model.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Timer;

@Controller
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;

    public ChatController(SimpMessagingTemplate messagingTemplate){
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/sendMessage")
    @SendTo("/topic/message")
    public ChatMessage sendMessage(ChatMessage message){
        message.setTime(LocalDateTime.now());
        return message;
    }

    @MessageMapping("/private-message")
    public void sendPrivateMessage(@Payload ChatMessage message){
        message.setTime(LocalDateTime.now());
        messagingTemplate.convertAndSendToUser(
                message.getReceiver(), "/queue/private", message
        );
    }

    @GetMapping("/chat")
    public String chat(){
        return "chat";
    }
}
