package org.example.gravlltest.Controller;

import org.example.gravlltest.Model.OpenAIModels.OpenAiChatResponse;
import org.example.gravlltest.Model.OpenAIModels.OpenAiResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@RestController
public class OpenAIController {

    @Qualifier("openai")
    private final RestTemplate restTemplate;

    private String model="gpt-3.5-turbo";


    private String apiUrl="https://api.openai.com/v1/chat/completions";

    public OpenAIController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String chat(@RequestParam String prompt) {
        OpenAiResponseModel request = new OpenAiResponseModel(model, prompt);
       OpenAiChatResponse response = restTemplate.postForObject(apiUrl, request, OpenAiChatResponse.class);

        if (response == null || response.getChoices() == null || response.getChoices().isEmpty()) {
            return "No response";
        }

        return response.getChoices().getFirst().getMessage().getContent();
    }
}
