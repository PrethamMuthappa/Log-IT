package org.example.gravlltest.Model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class OpenAiChatResponse {

    private List<Choice>choices;

    public static class Choice{

        private int index;
        private OpenAiResponseModel.Message message;
    }
}
