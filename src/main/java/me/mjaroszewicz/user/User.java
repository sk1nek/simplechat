package me.mjaroszewicz.user;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {

    @GeneratedValue( strategy = GenerationType.AUTO)
    @Id
    private long userId;

    private String name;

    private String password;

    public User(){}

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
