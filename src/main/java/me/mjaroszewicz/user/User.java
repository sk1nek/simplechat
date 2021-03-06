package me.mjaroszewicz.user;

import javax.persistence.*;
import java.util.HashSet;

@Entity
@Table(name = "user")
public class User {

    @GeneratedValue( strategy = GenerationType.SEQUENCE)
    @Id
    private long userId;

    private String name;

    private String password;

    private HashSet<String> friendList = new HashSet<>();

    public User(){}

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public long getUserId() {
        return userId;
    }

    @SuppressWarnings("unused")
    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public HashSet<String> getFriendList() {
        return friendList;
    }

    @SuppressWarnings("unused")
    public void setFriendList(HashSet<String> friendList) {
        this.friendList = friendList;
    }

    public void addFriend(String friendName){
        friendList.add(friendName);
    }

    /**
     *
     * @param friendName - name of friend to be removed
     * @return true if removal was a success
     */
    public boolean removeFriend(String friendName){
        return friendList.remove(friendName);
    }

    @Override
    public String toString() {
        return String.format("User id: %d name: %s", userId, name);
    }
}
