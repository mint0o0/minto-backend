package com.example.mintobackend.service;


import com.example.mintobackend.entity.Festival;
import com.example.mintobackend.repository.FestivalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FestivalService {
    private final FestivalRepository festivalRepository;

    public List<Festival> getAllFestival(){
        return festivalRepository.findAll();
    }
    public Festival createFestivals(Festival festival){
        return festivalRepository.save(festival);
    }
}
