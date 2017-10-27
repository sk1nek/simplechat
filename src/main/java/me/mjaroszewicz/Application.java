package me.mjaroszewicz;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class Application {

    //TODO - split js
    //TODO - add aesthetic 'send' functionality
    //TODO - separate socket endpoints
    //TODO - css

    public static void main(String[] args){
        SpringApplication.run(Application.class);
    }

}
