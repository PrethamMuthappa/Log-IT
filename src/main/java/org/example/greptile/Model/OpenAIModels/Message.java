package org.example.greptile.Model.OpenAIModels;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Data
@JsonSerialize
public  class Message {
    private String role;
    private String content;

}