package com.example.youcontribute.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.example.youcontribute.models.Repository;
import com.example.youcontribute.repositories.RepositoryRepository;

import jakarta.transaction.Transactional;

@Service
public class RepositoryService {

    private final RepositoryRepository repositoryRepository;

    public RepositoryService(RepositoryRepository repositoryRepository) {
        this.repositoryRepository = repositoryRepository;
    }

    @Transactional
    public void create(String organization, String repository) {
        Repository r = Repository.builder().organization(organization).repository(repository).build();
        this.repositoryRepository.save(r);
    }

    public List<Repository> list() {
        return this.repositoryRepository.findAll();
    }
}
