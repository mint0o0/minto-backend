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

import java.util.ArrayList;

import java.util.List;


@Service
@RequiredArgsConstructor
public class FestivalService {
    private final FestivalRepository festivalRepository;


    @Transactional(readOnly = true)
    public Page<Festival> getFestivals(String name, int pageNo){
        Pageable pageable = PageRequest.of(pageNo, 5);
        return festivalRepository.findByNameContainsIgnoreCase(name, pageable);

    }
    @Transactional(readOnly = true)
    public Page<Festival> getFestivals(String name, String category, int pageNo){
        Pageable pageable = PageRequest.of(pageNo, 5);
        return festivalRepository.findByNameContainsAndCategory(name, category, pageable);

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

    public Festival insertNft(String id, Object nft){
        var festival = festivalRepository.findById(id).orElseThrow(
                () -> new RuntimeException("축제 없음")
        );
        if (festival.getNftList() == null){
            var nftList = new ArrayList<>();
            nftList.add(nft);
            festival.setNftList(nftList);
        } else {
            festival.getNftList().add(nft);
        }
        festivalRepository.save(festival);
        return festival;
    }
    public Integer nftCount(String id){
        var festival = festivalRepository.findById(id).orElseThrow(
                () -> new RuntimeException("축제 없음")
        );
        if (festival.getCount() == null){
            festival.setCount(0);
            festivalRepository.save(festival);
            return 0;
        }
        else {
             return festival.getCount();
        }
    }
    public Integer updateNftCount(String id){
        var festival = festivalRepository.findById(id).orElseThrow(
                () -> new RuntimeException("축제 없음")
        );
        if (festival.getCount() == null){
            festival.setCount(1);
            festivalRepository.save(festival);
            return 1;
        }
        else {
            int cnt = festival.getCount();
            festival.setCount(cnt+1);
            festivalRepository.save(festival);
            return cnt+1;
        }
    }

    public Object getNftList(String festivalId){
        var festival = festivalRepository.findById(festivalId).orElseThrow(
                () -> new RuntimeException("축제 없음")
        );
        if (festival.getNftList() == null){
            return new ArrayList<>();
        }
        else {
            return festival.getNftList();
        }
    }

    public Festival updateFestival(Festival festival){
        return festivalRepository.save(festival);
    }

    @Transactional(readOnly = true)
    public List<Festival> getFestivalsByAdminId(String adminId){
        return festivalRepository.findByAdminId(adminId);
    }
}
