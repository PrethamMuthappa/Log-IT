package org.example.gravlltest.RestTemplate;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

//@Configuration
//public class OpenAIResttemplate {
//
//    private static String key="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";
//
//    @Bean
//    @Qualifier("openai")
//    public RestTemplate openrest(){
//        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.getInterceptors().add(((request, body, execution) -> {
//            request.getHeaders().add("Authorization","Bearer " + key);
//            return execution.execute(request,body);
//        }));
//        return restTemplate;
//    }
//
//}
