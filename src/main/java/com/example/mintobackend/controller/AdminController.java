package com.example.mintobackend.controller;


import com.example.mintobackend.entity.Festival;
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
    @PutMapping("/festival/{id}/nft")
    public ResponseEntity<Festival> createNft(@PathVariable String id, @RequestBody Object nft){
        return new ResponseEntity<>(festivalService.insertNft(id, nft), HttpStatus.OK);
    }

    @GetMapping("/festival/nft/{festivalId}")
    public ResponseEntity<Object> getNftList(@PathVariable String festivalId){
        return new ResponseEntity<>(festivalService.getNftList(festivalId), HttpStatus.OK);
    }
}
