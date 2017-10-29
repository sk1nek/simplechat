package me.mjaroszewicz.chat;


import me.mjaroszewicz.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@EnableScheduling
public class WebSocketController {

    private static final Logger log = LoggerFactory.getLogger(WebSocketController.class);

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private MessageRepository msgRepo;


//    @MessageMapping("/chat.message.{username}")

//    @Scheduled(fixedDelay = 1500)
//    @SendTo("/topic/greetings")
////    @SubscribeMapping("/topic/greetings")
//    public void autoMessage() throws Exception{
//
//        Message msg = new Message();
//        msg.setContent("omg lol xdDDD");
//
//        simpMessagingTemplate.convertAndSend("/topic/greetings", msg);
//
//    }

    @MessageMapping("/private.{username}")
    @SendTo("/topic/greetings")
    public Message handlePrivateMessage(@DestinationVariable String username, Message msg) throws Exception {
        log.info(msg.getContent());
        log.warn(username);
        Thread.sleep(200);
        Message ret = new Message();
        ret.setContent(msg.getContent());

        return ret;
    }





}
