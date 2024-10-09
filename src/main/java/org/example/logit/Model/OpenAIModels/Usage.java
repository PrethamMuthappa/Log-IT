package org.example.logit.Model.OpenAIModels;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class Usage {
    private int prompt_tokens;
    private int completion_tokens;
    private int total_tokens;

}