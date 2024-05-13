package com.example.mintobackend.controller;


import com.example.mintobackend.entity.Festival;
import com.example.mintobackend.service.FestivalService;
import com.example.mintobackend.entity.Mission;
import com.example.mintobackend.entity.Statistics;
import com.example.mintobackend.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import com.example.mintobackend.service.MissionService;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final FestivalService festivalService;

    @PostMapping("/festival")
    public ResponseEntity<Object> addFestival(@RequestBody HashMap<String,Object> festival) {
        return new ResponseEntity<>(festivalService.addFestival(festival), HttpStatus.CREATED);
    }
    @PutMapping("/festival/{id}/nft")
    public ResponseEntity<Festival> createNft(@PathVariable String id, @RequestBody Object nft){
        return new ResponseEntity<>(festivalService.insertNft(id, nft), HttpStatus.OK);
    }

    @GetMapping("/festival/nft/{festivalId}")
    public ResponseEntity<Object> getNftList(@PathVariable String festivalId){
        return new ResponseEntity<>(festivalService.getNftList(festivalId), HttpStatus.OK);
    }
	private final MissionService missionService;
	private final FestivalService festivalService;
	private final StatisticsService statisticsService;

/*	@GetMapping("/festivals")
	public ResponseEntity<List<Festival>> getFestivalsByAdminId(@RequestParam String adminId) {
		return new ResponseEntity<>(festivalService.getFestivalsByAdminId(adminId), HttpStatus.OK);
	}*/

	@PostMapping("/festivals/create")
	public ResponseEntity<Festival> createFestival(@RequestBody Festival festival) {
		return new ResponseEntity<>(festivalService.createFestivals(festival), HttpStatus.CREATED);
	}

	@PutMapping("/{festivalId}/update")
	public ResponseEntity<Festival> updateFestival(@RequestBody Festival festival, @PathVariable String festivalId) {
		return new ResponseEntity<>(festivalService.updateFestival(festival), HttpStatus.OK);
	}

	@PostMapping("/{festivalId}/missions/create")
	public ResponseEntity<Mission> createMission(@RequestBody Mission mission, @PathVariable String festivalId) {
		return new ResponseEntity<>(missionService.createMission(mission), HttpStatus.CREATED);
	}

	@GetMapping("/{festivalId}/missions")
	public ResponseEntity<List<Mission>> getMissionsByFestivalId(@PathVariable String festivalId) {
		return new ResponseEntity<>(missionService.getAllMissionsByFestivalId(festivalId), HttpStatus.OK);
	}

	@GetMapping("/{festivalId}/statistics")
	public ResponseEntity<List<Statistics>> getStatistics(@PathVariable String festivalId) {
		return new ResponseEntity<>(statisticsService.getStatisticsByFestivalId(festivalId), HttpStatus.OK);
	}

/*	@PutMapping("/{festivalId}/missions/{missionId}/update")
	public ResponseEntity<Mission> updateMission(@RequestBody Mission mission) {
		return new ResponseEntity<>(missionService.updateMission(mission), HttpStatus.OK);
	}*/

/*	@DeleteMapping("/{missionId}")
	public ResponseEntity<Void> deleteMission(@PathVariable String missionId) {
		missionService.deleteMission(missionId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}*/
}
