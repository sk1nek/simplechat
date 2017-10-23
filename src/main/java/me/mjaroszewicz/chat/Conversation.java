package me.mjaroszewicz.chat;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.HashSet;
import java.util.LinkedList;

@Entity
public class Conversation {

    @Autowired
    MessageRepository msgRepo;

    @Id
    @GeneratedValue
    private long id;

    private LinkedList<Long> messageIds;

    //contains convo participant ids
    private HashSet<Long> participants;

    public LinkedList<Long> getMessageIds() {
        return messageIds;
    }

    public void setMessageIds(LinkedList<Long> messageIds) {
        this.messageIds = messageIds;
    }

    public HashSet<Long> getParticipants() {
        return participants;
    }

    public void setParticipants(HashSet<Long> participants) {
        this.participants = participants;
    }

    public String getLastMessage(){
        if(messageIds != null)
            return msgRepo.findOne(messageIds.getLast()).getContent();
        else
            return "";
    }
}
