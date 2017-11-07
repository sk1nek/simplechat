package me.mjaroszewicz.user;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * User registration form DTO
 */
public class RegistrationForm {

    @NotNull
    @Size(min = 3, max = 20)
    private String name;

    @NotNull
    @Size(min = 6, max = 12)
    private String password;


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
