package me.mjaroszewicz.chat;


import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

@Controller
@EnableScheduling
public class WebSocketController {




//    @MessageMapping("/chat.message.{username}")

    @Scheduled(fixedDelay = 1500)
    @MessageMapping("/topic/greetings")
    public Message autoMessage() throws Exception{

        Message msg = new Message();


        return msg;
    }





}
