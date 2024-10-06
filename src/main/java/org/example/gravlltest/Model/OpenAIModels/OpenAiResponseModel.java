package org.example.gravlltest.Model.OpenAIModels;


import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class OpenAiResponseModel {

    private String model;
    private List<Message> messages;
    private int n;
    private double temperature;

    public OpenAiResponseModel(String model, String prompt) {
        this.model = model;

        this.messages = new ArrayList<>();
        this.messages.add(new Message("user", prompt));
    }

}
