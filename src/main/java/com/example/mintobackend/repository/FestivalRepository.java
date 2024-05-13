package com.example.mintobackend.repository;

import com.example.mintobackend.entity.Festival;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface FestivalRepository extends MongoRepository<Festival, String> {
    Page<Festival> findByNameContainsIgnoreCaseOrderByStartTime(String name, Pageable pageable);
    Page<Festival> findByNameContainsIgnoreCaseAndCategoryOrderByStartTime(String name, String category, Pageable pageable);
}
