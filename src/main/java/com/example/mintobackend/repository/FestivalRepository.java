package com.example.mintobackend.repository;

import com.example.mintobackend.entity.Festival;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface FestivalRepository extends MongoRepository<Festival, String> {

	Page<Festival> findByNameContainsIgnoreCase(String name, Pageable pageable);

	Page<Festival> findByNameContainsAndCategory(String name, String category, Pageable pageable);

	List<Festival> findByAdminId(String adminId);
}
