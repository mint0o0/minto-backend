package com.example.mintobackend.controller;


import com.example.mintobackend.service.FestivalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final FestivalService festivalService;

    @PostMapping("/festival")
    public ResponseEntity<Object> addFestival(@RequestBody HashMap<String,Object> festival) {
        return new ResponseEntity<>(festivalService.addFestival(festival), HttpStatus.CREATED);
    }

}
