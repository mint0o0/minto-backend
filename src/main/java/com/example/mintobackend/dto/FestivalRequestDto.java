package com.example.mintobackend.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
public class FestivalRequestDto {
    private String name;
    private String startTime;
    private String endTime;
    private List<String> imageList;
    private String location;
    private String latitude;
    private String longitude;
    private String price;
    private String host;
    private String description;
    private String category;
    private String phone;
    private String instagram;

//    public Festival toEntity (FestivalRequestDto festivalRequestDto) {
//        return new Festival(festivalRequestDto);
//    }
}
