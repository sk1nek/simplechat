package me.mjaroszewicz;

import java.time.LocalDateTime;
import java.util.Date;

public class Message {

    private LocalDateTime timestamp;

    private long authorId;

    private String content;

    public Message() {}

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
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
}
