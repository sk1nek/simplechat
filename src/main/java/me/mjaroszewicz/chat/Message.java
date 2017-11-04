package me.mjaroszewicz.chat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;


@Entity
public class Message implements Comparable<Message>{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Long timestamp;

    private long authorId;

    private long targetId;

    private String content;

    public Message() {}

    public Message(String content){
        this.content = content;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getTargetId() {
        return targetId;
    }

    public void setTargetId(long targetId) {
        this.targetId = targetId;
    }

    /**
     * Function that compares messages by chronological order
     * @param message Message to be compared
     * @return -1 if message is older, 0 if timestamps are equal, 1 if it is younger
     */
    @Override
    public int compareTo(Message message) {
        return this.timestamp.compareTo(message.timestamp);
    }

    @Override
    public String toString(){
        return String.format("Message author %s recipient %s timestamp %d content %s", authorId, targetId, timestamp, content);
    }
}
