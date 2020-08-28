package com.test.study.guides;

import com.test.study.entity.Quote;
import com.test.study.entity.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


//@Configuration
public class ConsumingRestApplication {


    private static final Logger log = LoggerFactory.getLogger(ConsumingRestApplication.class);


    @Bean(initMethod = "init01", destroyMethod = "destroy01")
    public User user02() {
        return new User(66L, "ww", 66, "66.com");
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
        return args -> {
            Quote quote = restTemplate.getForObject(
                    "https://gturnquist-quoters.cfapps.io/api/random", Quote.class);
            log.info(quote.toString());
        };
    }
}