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
        ArrayList<Message> query = msgRepo.findAllByAuthorIdAndTargetId(author, recipient);
        query.forEach(e -> System.out.println(e));
        log.info("xd");
        ArrayList<Message> ret = new ArrayList<>();
        if(!query.isEmpty()){
            ret.addAll(query.subList(query.size() - amount, query.size() - 1));
            Collections.sort(ret);
        }

        return ret;
    }



}
