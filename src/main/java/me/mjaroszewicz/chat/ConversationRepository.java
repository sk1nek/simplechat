package me.mjaroszewicz.chat;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

@Repository
public class ConversationRepository {

    @Autowired
    private MessageRepository msgRepo;

    /**
     *
     * @param author author ID as long
     * @param recipient recipient ID as long
     * @param amount amount of messages to process
     * @return array of Message objects sorted by timestamps
     */
    public ArrayList<Message> getLatestMessages(Long author, Long recipient, int amount) {
        ArrayList<Message> query = msgRepo.findAllByAuthorIdAndTargetId(author, recipient);
        ArrayList<Message> ret = new ArrayList<>();
        if(!query.isEmpty()){
            ret.addAll(query.subList(query.size() - amount, query.size() - 1));
            Collections.sort(ret);
        }

        return ret;
    }



}
