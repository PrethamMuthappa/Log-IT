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
public class ChatRequest {

    private String model;
    private List<Message> messages;
    private int max_tokens;
    private double temperature;
    private double top_p;
    private int top_k;
    private double repetition_penalty;
    private List<String> stop;
    private int truncate;
    private boolean stream;

}
