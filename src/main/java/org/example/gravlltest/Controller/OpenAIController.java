package org.example.gravlltest.Controller;

import org.example.gravlltest.Model.OpenAIModels.Chatresponse;
import org.example.gravlltest.Model.OpenAIModels.ChatRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class OpenAIController {

    private final RestTemplate restTemplate;

    private String model="gpt-3.5-turbo";

   private String key="08a1ab7aaff2c92e0aae9bf7ee6a599a17f3ae62f749202646b68732ab0a5ec0";
    private String apiUrl="https://api.together.xyz/v1/chat/completions";

    public OpenAIController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Chatresponse generateChat(ChatRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(key);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ChatRequest> entity = new HttpEntity<>(request, headers);

        return restTemplate.postForObject(apiUrl, entity, Chatresponse.class);
    }
}
