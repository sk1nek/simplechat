package me.mjaroszewicz.chat;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

@Repository
public class ConversationRepository {

    @Autowired
    private MessageRepository msgRepo;

    private final static Logger log = LoggerFactory.getLogger(ConversationRepository.class);

    /**
     *
     * @param author author ID as long
     * @param recipient recipient ID as long
     * @param amount amount of messages to process
     * @return array of Message objects sorted by timestamps
     */
    public ArrayList<Message> getLatestMessages(Long author, Long recipient, int amount) {
        //doc dis properly
        log.warn(String.format("Probing for author %d and recipient %d", author, recipient));
        ArrayList<Message> query = msgRepo.findByAuthorIdAndTargetId(author, recipient);
        query.addAll(msgRepo.findAllByAuthorIdAndTargetId(recipient, author));
        query.forEach(e -> System.out.println(e));
        log.info("xd");
        ArrayList<Message> ret = new ArrayList<>();
        if(!query.isEmpty()){
            ret.addAll(query.subList(0, query.size() - 1));
            Collections.sort(ret);
        }

        msgRepo.findAll().forEach(e -> log.error(e.toString()));

        return ret;
    }



}
