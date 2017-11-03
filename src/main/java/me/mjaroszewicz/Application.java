package me.mjaroszewicz;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    //TODO - manage friend list
    //TODO - link between subpages
    //TODO - fully server-sided user identification (currently there's some weird stuff in websocket controller)

    public static void main(String[] args){
        SpringApplication.run(Application.class);
    }

}
