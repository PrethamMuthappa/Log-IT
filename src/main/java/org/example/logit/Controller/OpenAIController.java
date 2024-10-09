package org.example.logit.Controller;

import org.example.logit.Model.OpenAIModels.Chatresponse;
import org.example.logit.Model.OpenAIModels.ChatRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class OpenAIController {

    private final RestTemplate restTemplate;

   @Value("${mykeys.apikey}")
   private String key;
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
