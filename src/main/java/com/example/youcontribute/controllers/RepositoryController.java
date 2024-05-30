package com.example.youcontribute.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.youcontribute.controllers.requests.CreateRepositoryRequest;
import com.example.youcontribute.controllers.resources.RepositoryResource;
import com.example.youcontribute.service.RepositoryService;

@RestController
@RequestMapping("/repositories")
public class RepositoryController {

    private final RepositoryService repositoryService;

    public RepositoryController(RepositoryService repositoryService) {
        this.repositoryService = repositoryService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody CreateRepositoryRequest request) {
        System.out.println("Creating repository: " + request.getOrganization() + "/" + request.getRepository());
        this.repositoryService.create(request.getOrganization(), request.getRepository());
    }

    @GetMapping
    public List<RepositoryResource> list() {
        return RepositoryResource.createFor(this.repositoryService.list());
    }
}
