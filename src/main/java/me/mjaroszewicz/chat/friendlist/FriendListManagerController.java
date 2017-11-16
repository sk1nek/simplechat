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

/**
 * Component that handles incoming friend operation requests and provides user with response objects
 */
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
     * Method that handles adding of friends. Sends instance of FriendOperationFeedback class back to the user.
     * If username is not found inside repository, 'success' flag is set to false.
     *
     * @param friendname - username of friend to be added
     */
    @MessageMapping("/addFriend")
    public void handleFriendAddRequest(@Payload String friendname){

        User usr = getLoggedUser();

        if(usr.getFriendList().contains(friendname))
            sendFeedback(new FriendOperationFeedback(friendname, false, "add"));
        else if(userRepo.findOneByName(friendname) != null && !friendname.equals(usr.getName())){
            usr.addFriend(friendname);
            userRepo.save(usr);
            sendFeedback(new FriendOperationFeedback(friendname, true, "add"));
        }else
            sendFeedback(new FriendOperationFeedback(friendname, false, "add"));

    }

    /**
     *
     * Method handling friend removal requests. Sends FriendOperationFeedback to user.
     *
     * @param friendname - username of friend to be removed
     */
    @MessageMapping("/removeFriend")
    public void handleRemoveFriend(@Payload String friendname){

        User usr = getLoggedUser();
        boolean successFlag = false;

        if(!friendname.isEmpty()){
            successFlag = usr.removeFriend(friendname);
            userRepo.save(usr);
            sendFeedback(new FriendOperationFeedback(friendname, successFlag, "remove"));
        }else
            //noinspection ConstantConditions
            sendFeedback(new FriendOperationFeedback(friendname, successFlag, "remove"));


    }

    private User getLoggedUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepo.findOneByName(auth.getName());
    }

    private void sendFeedback(FriendOperationFeedback fof){
        simpMessagingTemplate.convertAndSendToUser(getLoggedUser().getName(), "/friendFeedback", fof);
    }


}
