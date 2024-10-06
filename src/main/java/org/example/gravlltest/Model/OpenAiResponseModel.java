package org.example.gravlltest.Model;


import lombok.*;

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

    public static class Message{
        private String role;
        private String content;
    }

}
