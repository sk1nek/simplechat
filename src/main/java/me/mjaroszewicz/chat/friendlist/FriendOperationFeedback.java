package me.mjaroszewicz.chat.friendlist;

/**
 * Response object sent to user after handling requested user operation.
 */
public class FriendOperationFeedback {

    private boolean success;

    private String friendName;

    private String type;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public FriendOperationFeedback(){}

    public FriendOperationFeedback(String name, boolean success, String type){
        this.friendName = name;
        this.success = success;
        this.type = type;
    }
}
