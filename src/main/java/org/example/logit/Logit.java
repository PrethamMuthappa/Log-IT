package org.example.logit;

import org.example.logit.RestTemplate.Keys;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(Keys.class)
@SpringBootApplication
public class Logit {

    public static void main(String[] args) {
        SpringApplication.run(Logit.class, args);
    }

}
