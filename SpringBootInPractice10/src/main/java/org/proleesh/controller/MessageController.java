package org.proleesh.controller;

import lombok.extern.slf4j.Slf4j;
import org.proleesh.model.InputMessage;
import org.proleesh.model.OutputMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.Clock;
import java.time.Instant;

@Slf4j
@Controller
public class MessageController {

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public OutputMessage message(InputMessage message){
        log.info("Input message " + message);
        return OutputMessage.builder()
                .time(Instant.now(Clock.systemDefaultZone()))
                .content(message.getContent())
                .build();
    }
}
