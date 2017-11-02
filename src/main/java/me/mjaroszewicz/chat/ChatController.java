package me.mjaroszewicz.chat;


import me.mjaroszewicz.user.User;
import me.mjaroszewicz.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.HashSet;

@Controller
public class ChatController {

    private final static Logger log = LoggerFactory.getLogger(ChatController.class);

    private static int DEFAULT_MSG_AMOUNT = 30;

    @Autowired
    UserRepository userRepo;

    @Autowired
    ConversationRepository conRepo;

    @GetMapping(value = {"","/"})
    public String getLanding(){
        return "redirect:/chat/";
    }

    @GetMapping(value = {"/chat","/chat/"})
    public String getConversations(Model mdl){

        //identifying user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        User user = userRepo.findOneByName(username);

        HashSet<String> hs = new HashSet<>();
        hs.add("bar");
        hs.add("friend2");
        hs.add("friend3");
        user.setFriendList(hs);

        mdl.addAttribute("friends", user.getFriendList());

        return "conversations";
    }

    @GetMapping("/chat/{user}/")
    public String getConversationScreen(@PathVariable String user, Model mdl) {

        //identifying user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String loggedUserName = auth.getName();

        log.info(user);

        //target user
        Long targetId = userRepo.findOneByName(user).getUserId();
        //logged usr
        Long loggedId = userRepo.findOneByName(loggedUserName).getUserId();


        ArrayList<Message> conversation = new ArrayList<>(30);
        conversation.addAll(conRepo.getLatestMessages(loggedId, targetId, DEFAULT_MSG_AMOUNT));


        if(conversation.isEmpty())
            mdl.addAttribute("isEmpty", "true");
        else
            mdl.addAttribute("isEmpty", "false");

        mdl.addAttribute("conversation", conversation);
        mdl.addAttribute("loggedUserId", loggedId); //helps with dividing messages
        mdl.addAttribute("loggedUserName", loggedUserName);
        mdl.addAttribute("targetUserId", targetId);
        mdl.addAttribute("targetUserName", userRepo.findOne(targetId).getName());
        return "conversation";
    }

}
