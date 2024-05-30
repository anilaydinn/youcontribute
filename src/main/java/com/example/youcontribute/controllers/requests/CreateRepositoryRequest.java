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
public class CreateRepositoryRequest {
    @JsonProperty("organization")
    private String organization;

    @JsonProperty("repository")
    private String repository;

    @JsonCreator
    public CreateRepositoryRequest(@JsonProperty("organization") String organization,
            @JsonProperty("repository") String repository) {
        this.organization = organization;
        this.repository = repository;
    }
}
