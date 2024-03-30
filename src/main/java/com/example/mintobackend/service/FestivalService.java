package com.example.mintobackend.service;


import com.example.mintobackend.entity.Festival;
import com.example.mintobackend.exception.CannotFindElementException;
import com.example.mintobackend.repository.FestivalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class FestivalService {
    private final FestivalRepository festivalRepository;


    @Transactional(readOnly = true)
    public Page<Festival> getFestivals(String name, int pageNo){
        Pageable pageable = PageRequest.of(pageNo, 5);
        return festivalRepository.findByNameContains(name, pageable);

    }
    @Transactional(readOnly = true)
    public Festival getFestival(String id){
        return festivalRepository.findById(id).orElseThrow(
                ()-> new CannotFindElementException("축제가 없습니다.")
        );
    }
    public Festival createFestivals(Festival festival){
        return festivalRepository.save(festival);
    }
}
