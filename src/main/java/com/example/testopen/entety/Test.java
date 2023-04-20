package com.example.testopen.entety;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Test {
    @JsonProperty("quest")
    private List<Quest> quest;
    @JsonProperty("time")
    private String time;
}