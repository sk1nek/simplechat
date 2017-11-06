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

/**
 * The main purpose of this component is merging database queries to the form of sorted message array
 */
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

        if(results.size() == 0 || results.size() < amount) {
            return results;
        }
        else {
            log.info(String.format("Size %d amount %d", results.size(), amount));
            ArrayList<Message> ret = new ArrayList<>(30);
            ret.addAll(results.subList(results.size() - amount, results.size() - 1));
            return ret;
        }

    }




}
