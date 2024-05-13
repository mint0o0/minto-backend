package com.example.mintobackend.service;

import com.example.mintobackend.entity.Statistics;
import com.example.mintobackend.repository.StatisticsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticsService {
	private final StatisticsRepository statisticsRepository;

	@Transactional(readOnly = true)
	public List<Statistics> getStatisticsByFestivalId(String festivalId) {
		return statisticsRepository.findByFestivalId(festivalId);
	}
}
