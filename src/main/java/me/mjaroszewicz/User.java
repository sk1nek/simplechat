package me.mjaroszewicz;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@Entity
public class User {

    @GeneratedValue
    private long userId;

    private String name;

    private String hashedPassword;

    public User(){}

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }
}
