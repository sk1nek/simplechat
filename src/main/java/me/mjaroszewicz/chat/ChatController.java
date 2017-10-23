package me.mjaroszewicz.chat;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ChatController {


    @GetMapping(value = {"/chat","/chat/"})
    public String getConversations(Model mdl){

        //identifying user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();


        return "convos";
    }

}
