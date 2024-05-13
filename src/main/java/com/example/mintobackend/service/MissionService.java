package com.example.mintobackend.service;

import com.example.mintobackend.entity.Festival;
import com.example.mintobackend.entity.Mission;
import com.example.mintobackend.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionService {
	private final MissionRepository missionRepository;
	private final FestivalService festivalService;

	@Transactional(readOnly = true)
	public List<Mission> getAllMissionsByFestivalId(String festivalId) {
		return missionRepository.findByFestivalId(festivalId);
	}

	@Transactional(readOnly = true)
	public Mission getMissionById(String missionId) {
		return missionRepository.findById(missionId).orElse(null);
	}

	public Mission createMission(Mission mission) {
		Festival festival = festivalService.getFestival(mission.getFestivalId());
		festival.getMissions().add(mission);
		festivalService.updateFestival(festival);

		return missionRepository.save(mission);
	}

	public Mission updateMission(Mission mission) {
		Festival festival = festivalService.getFestival(mission.getFestivalId());

		for (int i = 0; i < festival.getMissions().size(); i++) {
			if (festival.getMissions().get(i).getMissionId() != null &&
					festival.getMissions().get(i).getMissionId().equals(mission.getMissionId())) {
				festival.getMissions().set(i, mission);
				break;
			}
		}
		festivalService.updateFestival(festival);

		return missionRepository.save(mission);
	}

	public void deleteMission(String id) {
		missionRepository.deleteById(id);
	}
}
