package me.mjaroszewicz;


import me.mjaroszewicz.user.User;
import me.mjaroszewicz.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@DependsOn("userRepo")
public class MockEntityPopulator {

    @Autowired
    UserRepository userRepo;

    Logger log = LoggerFactory.getLogger(MockEntityPopulator.class);


    @PostConstruct
    private void addMockUsers() {
        userRepo.save(new User("foo", "barbar"));
        userRepo.save(new User("bar", "foofoo"));
        log.info("Test users saved");
    }
}
