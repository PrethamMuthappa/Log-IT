package org.example.gravlltest.Model.OpenAIModels;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public  class Message{
    private String role;
    private String content;

}