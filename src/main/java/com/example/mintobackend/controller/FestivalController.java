package com.example.mintobackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/festival")
public class FestivalController {

    // for testing
    @GetMapping("/{id}")
    public String getFestival(@PathVariable Integer id){
        System.out.println(id);
        return id.toString();
    }
}
