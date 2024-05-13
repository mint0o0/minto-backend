package com.example.mintobackend.repository;

import com.example.mintobackend.entity.Statistics;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface StatisticsRepository extends MongoRepository<Statistics, String> {
	List<Statistics> findByFestivalId(String festivalId);
}
