package com.example.mintobackend.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
public class Mission {
	@Id
	private String missionId;
	private String festivalId;
	private String name;
	private String description;
	private String location;
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private List<String> imageList;
}
