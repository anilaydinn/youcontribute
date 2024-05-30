package com.example.youcontribute.controllers;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.example.youcontribute.controllers.requests.CreateRepositoryRequest;
import com.example.youcontribute.controllers.resources.RepositoryResource;
import com.example.youcontribute.service.RepositoryService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
        log.info("Received request to create repository: {}", request);
        this.repositoryService.create(request.getOrganization(), request.getRepository());
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RepositoryResource> list() {
        return RepositoryResource.createFor(this.repositoryService.list());
    }
}
