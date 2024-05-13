package com.example.mintobackend.controller;

import com.example.mintobackend.dto.FestivalRequestDto;
import com.example.mintobackend.entity.Festival;
import com.example.mintobackend.repository.FestivalRepository;
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
    private final FestivalRepository festivalRepository;

    // for testing
    @GetMapping("/{id}")
    public ResponseEntity<Festival> getFestival(@PathVariable String id){
        System.out.println(id);
        return new ResponseEntity<>(festivalService.getFestival(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<Festival>> getFestivals(
        @RequestParam(required = false, defaultValue = "0", value = "page" ) Integer pageNo,
        @RequestParam(required = false, defaultValue = "", value = "name") String name,
        @RequestParam(required = false, defaultValue = "", value = "category") String category){
        if (category.isEmpty()){
            return new ResponseEntity<>(festivalService.getFestivals(name, pageNo), HttpStatus.OK);
        }
        return new ResponseEntity<>(festivalService.getFestivals(name, category, pageNo), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Festival> createFestival(@RequestBody Festival festival){
        return null;
    }
    @PutMapping
    public ResponseEntity<Festival> updateFestival(@RequestBody FestivalRequestDto dto){
        return null;
    }

    @GetMapping("/{id}/nft/count")
    public ResponseEntity<Integer> countSendNft(@PathVariable String id){
        return new ResponseEntity<>(festivalService.nftCount(id), HttpStatus.OK);
    }
    @PutMapping("/{id}/nft/count")
    public ResponseEntity<Integer> updateSendNft(@PathVariable String id){
        return new ResponseEntity<>(festivalService.updateNftCount(id), HttpStatus.OK);
    }


}
