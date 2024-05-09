package com.example.mintobackend.dto;

import com.example.mintobackend.entity.Festival;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class VisitFestivalDto {
    private String id;
    private String name;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private List<String> imageList;
    private String location;
    private String description;
    private List<HashMap<String, Object>> missions;
    private Object visitDate;
    private String category;
    
    public VisitFestivalDto(Festival festival) {
        this.id = festival.getId();
        this.name = festival.getName();
        this.startTime = festival.getStartTime();
        this.endTime = festival.getEndTime();
        this.imageList = festival.getImageList();
        this.location = festival.getLocation();
        this.description = festival.getDescription();
        this.missions = festival.getMissions();
        this.category = festival.getCategory();
    }
}
