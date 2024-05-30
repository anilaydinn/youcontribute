package com.example.youcontribute.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.youcontribute.models.Repository;

public interface RepositoryRepository extends PagingAndSortingRepository<Repository, Integer> {
    List<Repository> findAll();

    void save(Repository r);
}
