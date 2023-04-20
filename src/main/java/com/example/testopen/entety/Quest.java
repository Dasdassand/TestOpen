package com.example.testopen.entety;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Quest {
    @JsonProperty("id")
    private String id;
    @JsonProperty("answer")
    private List<Answer> answer = new ArrayList<>();
    @JsonProperty("quest")
    private String quest;

    public record Answer(String answer, boolean status) {
    }
}
