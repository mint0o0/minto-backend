package com.example.mintobackend.controller;


import com.example.mintobackend.entity.Festival;
import com.example.mintobackend.service.FestivalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final FestivalService festivalService;

    @PostMapping("/festival")
    public ResponseEntity<Object> addFestival(@RequestBody HashMap<String,Object> festival) {
        return new ResponseEntity<>(festivalService.addFestival(festival), HttpStatus.CREATED);
    }
    @PutMapping("/festival/{festivalId}/nft")
    public ResponseEntity<Festival> createNft(@PathVariable String festivalId, @RequestBody Object nft){
        return new ResponseEntity<>(festivalService.insertNft(festivalId, nft), HttpStatus.OK);
    }

    @PutMapping("/festival/{festivalId}/imageList")
    public ResponseEntity<Object> addImageList(@PathVariable String festivalId, @RequestBody List<String> imageList){
        return new ResponseEntity<>(festivalService.insertImageList(festivalId, imageList), HttpStatus.OK);
    }

    @GetMapping("/festival/nft/{festivalId}")
    public ResponseEntity<Object> getNftList(@PathVariable String festivalId){
        return new ResponseEntity<>(festivalService.getNftList(festivalId), HttpStatus.OK);
    }
}
