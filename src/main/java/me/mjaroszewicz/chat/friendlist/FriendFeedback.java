package me.mjaroszewicz.chat.friendlist;

/**
 * DTO simplifying friendlist management
 */
public class FriendFeedback {

    private boolean success;

    private String friendName;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }

    public FriendFeedback(){}

    public FriendFeedback(String name, boolean success){
        this.friendName = name;
        this.success = success;
    }
}
