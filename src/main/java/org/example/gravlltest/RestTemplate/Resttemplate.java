package org.example.gravlltest.RestTemplate;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Resttemplate {

    @Bean
    public RestTemplate restTemplate (RestTemplateBuilder builder){
        return builder.build();
    }
}
