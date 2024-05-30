package com.example.youcontribute.controllers.requests;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(onConstructor = @__(@JsonCreator))
public class CreateRepositoryRequest {
    @JsonProperty("organization")
    private String organization;

    @JsonProperty("repository")
    private String repository;
}
