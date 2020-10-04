package com.test.study.guides.messagingstompwebsocket;


import com.test.study.entity.Customer;
import com.test.study.entity.User;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.concurrent.TimeUnit;

@Controller
public class GreetingController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Customer greeting(User user) throws InterruptedException {
        TimeUnit.SECONDS.sleep(10);
        return new Customer(user.getId(), user.getName(), user.getEmail());
    }
}
