package me.mjaroszewicz;


import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class GreetingController {

//    @MessageMapping("/hello")
//    @SendTo("/topic/greetings")
//    public Greeting greeting(HelloMessage msg) throws Exception{
//
//        Thread.sleep(1000);
//        return new Greeting("Hello, " + msg.getName() + " !");
//    }

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting autoGreet() throws Exception {

        Thread.sleep(500);

        System.out.println("lold");

        return new Greeting("OMg lol");
    }
}
