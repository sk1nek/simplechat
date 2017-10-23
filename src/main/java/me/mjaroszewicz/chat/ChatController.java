package me.mjaroszewicz.chat;


import me.mjaroszewicz.user.User;
import me.mjaroszewicz.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

@Controller
public class ChatController {

    @Autowired
    UserRepository userRepo;

    @Autowired
    ConversationRepository conRepo;

    @GetMapping(value = {"/chat","/chat/"})
    public String getConversations(Model mdl){

        //identifying user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        User user = userRepo.findOneByName(username);
        ArrayList<Conversation> convos = (ArrayList<Conversation>) conRepo.findAllByParticipants(user.getUserId());

        //passing conversations to model
        mdl.addAttribute("conversations", convos);


        return "conversations";
    }

}
