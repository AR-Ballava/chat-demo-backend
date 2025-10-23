package com.chat.app.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ChatMessage {
    private Long id;
    private String sender;
    private String receiver;
    private String content;
    private LocalDateTime time;
}
