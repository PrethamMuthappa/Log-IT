package org.example.greptile.Model.OpenAIModels;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class Choice{
    private Message message;
    private String finish_reason;
    private int index;
}