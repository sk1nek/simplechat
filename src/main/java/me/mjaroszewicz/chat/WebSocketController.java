package me.mjaroszewicz.chat;


import me.mjaroszewicz.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

@Controller
@EnableScheduling
public class WebSocketController {

    private static final Logger log = LoggerFactory.getLogger(WebSocketController.class);

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private MessageRepository msgRepo;

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    public WebSocketController(SimpMessagingTemplate template){
        this.template = template;
    }


    @MessageMapping("/private")
    public void handlePrivateMessage(@Payload Message msg) throws Exception {
        Message ret = new Message();
        ret.setContent(msg.getContent());
        ret.setTimestamp(System.currentTimeMillis());
        ret.setAuthorId((msg.getAuthorId()));
        ret.setTargetId(msg.getTargetId());
        msgRepo.save(ret);

        log.info("Saved message" + ret.toString());

        template.convertAndSendToUser(userRepo.findOne(msg.getTargetId()).getName(), "/private/incoming", msg);
    }

}
