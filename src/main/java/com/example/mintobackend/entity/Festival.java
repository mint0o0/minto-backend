package com.example.mintobackend.entity;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Document(collection = "festival")
@Getter
@ToString
public class Festival {
    @Id
    private String id;
    private String name;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private List<String> imageList;
    private String location;
    private String description;
    private List<HashMap<String, Object>> missions;
    private String category;
}
