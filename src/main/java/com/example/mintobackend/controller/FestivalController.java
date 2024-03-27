package com.example.mintobackend.controller;

import com.example.mintobackend.entity.Festival;
import com.example.mintobackend.service.FestivalService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



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
    public ResponseEntity<Page<Festival>> getFestivals(
        @RequestParam(required = false, defaultValue = "0", value = "page" ) Integer pageNo,
        @RequestParam(required = false, defaultValue = "", value = "name") String name){
        return new ResponseEntity<>(festivalService.getFestivals(name, pageNo), HttpStatus.OK);
    }

}
