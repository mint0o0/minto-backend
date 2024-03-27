package com.example.mintobackend.controller;

import com.example.mintobackend.entity.Festival;
import com.example.mintobackend.service.FestivalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/festival")
@RequiredArgsConstructor
public class FestivalController {

    private final FestivalService festivalService;
    // for testing
    @GetMapping("/{id}")
    public String getFestival(@PathVariable Integer id){
        System.out.println(id);
        return id.toString();
    }

    @GetMapping
    public List<Festival> getFestivals(){
        return festivalService.getAllFestival();
    }
}
