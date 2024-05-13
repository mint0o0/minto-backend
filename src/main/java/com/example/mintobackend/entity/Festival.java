package com.example.mintobackend.entity;

import com.example.mintobackend.dto.FestivalRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Document(collection = "festival")
@Getter
@ToString
@Setter
@Builder
public class Festival {
    @Id
    private String id;
    private String name;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private List<String> imageList;
    private String location;
    private String latitude;
    private String longitude;
    private String price;
    private String host;
    private String description;
    private List<HashMap<String, Object>> missions;
    private String category;
    private String phone;
    private String instagram;
    private List<Object> nftList;
    private Integer count;


//    public Festival(FestivalRequestDto dto){
//        this.name = dto.getName();
//        this.startTime = dto.getStartTime();
//        this.endTime = dto.getEndTime();
//        this.imageList = dto.getImageList();
//        this.location = dto.getLocation();
//        this.latitude = dto.getLatitude();
//        this.longitude = dto.getLongitude();
//        this.price = dto.getPrice();
//        this.host = dto.getHost();
//        this.description = dto.getDescription();
//        this.category = dto.getCategory();
//        this.phone = dto.getPhone();
//        this.instagram = dto.getInstagram();
//
//    }

}