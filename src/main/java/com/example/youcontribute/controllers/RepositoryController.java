package com.example.youcontribute.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController("/repositories")
public class RepositoryController {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create() {
    }
}
