package me.mjaroszewicz.chat.friendlist;


import me.mjaroszewicz.user.User;
import me.mjaroszewicz.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

@Controller
public class FriendListManagerController {

    private final static Logger log = LoggerFactory.getLogger(FriendListManagerController.class);

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    public FriendListManagerController(SimpMessagingTemplate template){
        this.simpMessagingTemplate = template;
    }


    /**
     *
     * Method that handles adding of friends. Sends instance of FriendFeedback class back to the user.
     * If username is not found inside repository, 'success' flag is set to false.
     *
     * @param friendname - username of friend to be added
     */
    @MessageMapping("/addFriend")
    public void handleFriendAddRequest(@Payload String friendname){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User usr = userRepo.findOneByName(auth.getName());

        if(userRepo.findOneByName(friendname) != null && !friendname.equals(usr.getName())){
            usr.addFriend(friendname);
            simpMessagingTemplate.convertAndSendToUser(usr.getName(), "/friendFeedback", new FriendFeedback(friendname, true));
            userRepo.save(usr);
        }else{
            simpMessagingTemplate.convertAndSendToUser(usr.getName(), "/friendFeedback", new FriendFeedback(friendname, false));
        }

    }


}
