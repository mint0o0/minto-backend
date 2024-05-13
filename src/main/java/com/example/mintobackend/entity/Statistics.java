package com.example.mintobackend.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Document(collection = "statistics")
@Getter
@Setter
@ToString
public class Statistics {
	@Id
	private String id;
	private String festivalId;
	private LocalDateTime date;
	private Integer localVisitor;
	private Integer foreignVisitor;
	private Integer localConsumption;
	private Integer foreignConsumption;
	private Integer restaurantConsumption;
	private Integer lodgingConsumption;
	private Integer shoppingConsumption;
	private Integer serviceConsumption;
	private List<HashMap<String, Integer>> completedMissionCount;
}
