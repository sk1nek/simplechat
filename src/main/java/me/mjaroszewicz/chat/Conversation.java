package me.mjaroszewicz.chat;

import java.util.HashSet;
import java.util.LinkedList;

public class Conversation {

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
}
