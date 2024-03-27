package com.example.mintobackend.repository;

import com.example.mintobackend.entity.Festival;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FestivalRepository extends MongoRepository<Festival, String> {
}
