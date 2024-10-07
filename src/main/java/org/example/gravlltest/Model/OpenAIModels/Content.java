package org.example.gravlltest.Model.OpenAIModels;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@JsonSerialize
public  class Content {
    private String type;
    private String text;

}