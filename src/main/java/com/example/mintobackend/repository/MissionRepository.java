package com.example.mintobackend.repository;

import com.example.mintobackend.entity.Mission;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MissionRepository extends MongoRepository<Mission, String> {
	List<Mission> findByFestivalId(String festivalId);
}
