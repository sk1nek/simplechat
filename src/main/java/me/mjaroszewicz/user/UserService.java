package me.mjaroszewicz.user;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class UserService {

    @Autowired
    public UserService(){}

    //user repo
    @Autowired
    private UserRepository userRepo;

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    /**
     *
     * @param form RegistrationForm object passed from register page
     */
    void registerUser(RegistrationForm form){

        if (isUniqueUsername(form.getName())) {
            User usr = new User();
            usr.setName(form.getName());
            usr.setPassword(form.getPassword());

            userRepo.save(usr);
            log.info(String.format("User %s saved to database", usr.getName()));

        }else{
            log.info("Could not create user named " + form.getName() + " - username taken");
        }

    }

    /**
     * Function that checks if username exists in database
     *
     * @param username - unique username
     * @return true if username already exists in db
     */
    private boolean isUniqueUsername(String username) {

        return userRepo.findOneByName(username) == null;

    }


    /**
     *
     * @param input String to be hashed
     * @return Result of passing input through MD5 algorithm
     */
    private String getMD5(String input) throws NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] bytes = md.digest(input.getBytes());
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < bytes.length ; ++i)
            sb.append(Integer.toHexString((bytes[i] & 0xFF) | 0x100).substring(1,3));

        return sb.toString();

    }








}
