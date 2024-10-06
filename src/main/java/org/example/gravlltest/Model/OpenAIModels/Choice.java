package org.example.gravlltest.Model.OpenAIModels;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class Choice{

    private int index;
    private Message message;

}