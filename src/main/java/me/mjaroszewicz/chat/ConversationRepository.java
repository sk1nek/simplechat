package me.mjaroszewicz.chat;


import me.mjaroszewicz.user.User;
import me.mjaroszewicz.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

@Repository
public class ConversationRepository {

    @Autowired
    private MessageRepository msgRepo;

    @Autowired
    private UserRepository userRepo;

    private final static Logger log = LoggerFactory.getLogger(ConversationRepository.class);

    private User getLoggedUser(){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();

        return userRepo.findOneByName(userName);
    }

    /**
     *
     * @param author author ID as long
     * @param recipient recipient ID as long
     * @param amount amount of messages to process
     * @return array of Message objects sorted by timestamps
     */
    public ArrayList<Message> getLatestMessages(Long author, Long recipient, int amount) {
        ArrayList<Message> results = msgRepo.findByAuthorIdAndTargetId(author, recipient);
        results.addAll(msgRepo.findAllByAuthorIdAndTargetId(recipient, author));

        ArrayList<Message> ret = new ArrayList<>();
        if(!results.isEmpty() && results.size() < amount){
            ret.addAll(results);
        }else if(!results.isEmpty() && results.size() >= amount){
            ret.addAll(results.subList(results.size() - 1 - amount, results.size() - 1));
        }

        Collections.sort(ret);

        return ret;
    }




}
