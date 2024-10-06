package org.example.gravlltest.Model.OpenAIModels;

import lombok.*;

import java.net.ContentHandler;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class OpenAiChatResponse {

    private List<Choice>choices;

}
