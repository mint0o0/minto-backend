package com.example.mintobackend.repository;

import com.example.mintobackend.entity.Festival;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface FestivalRepository extends MongoRepository<Festival, String> {
    Page<Festival> findByNameContainsIgnoreCase(String name, Pageable pageable);
    Page<Festival> findByNameContainsIgnoreCaseAndCategory(String name, String category, Pageable pageable);
}
