package com.example.mintobackend.service;

import com.example.mintobackend.dto.VisitFestivalDto;
import com.example.mintobackend.entity.Festival;
import com.example.mintobackend.exception.CannotFindElementException;
import com.example.mintobackend.repository.FestivalRepository;
import com.example.mintobackend.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final FestivalRepository festivalRepository;
    // member 가 방문한 축제 확인 하기
    @Transactional(readOnly = true)
    public List<VisitFestivalDto> getVisitedFestival(String walletAddress){
        var member = memberRepository.findByWalletAddress(walletAddress).orElseThrow(
                ()-> new CannotFindElementException("member가 없습니다.")
        );
        List<VisitFestivalDto> visitFestivalDtoList = new ArrayList<>();

        member.getVisitFestivals().keySet().forEach(festivalId -> {
            var festival = festivalRepository.findById(festivalId).orElseThrow(()->new CannotFindElementException("축제가 없습니다."));
            System.out.println(festivalId);
            System.out.println(festival.getName());
            System.out.println(festival);
            Object visitDate = member.getVisitFestivals().get(festivalId).get("visitDate");
            var visitFestival = new VisitFestivalDto(festival);
            visitFestival.setVisitDate(visitDate);
            visitFestivalDtoList.add(visitFestival);
        });

        return visitFestivalDtoList;
    }
    
    // member 미션 성공 -> 미션이 성공 등록 하면 자동으로 축제 기록하게 된다.
    @Transactional
    public  HashMap<String, HashMap<String, Object>> missionCompleted(String walletAddress, String festivalId, Integer missionIndex){
        var member = memberRepository.findByWalletAddress(walletAddress).orElseThrow(
                ()-> new CannotFindElementException("member가 없습니다.")
        );
        // 만약 이미 방문한 축제에 값이 존재 한다면
        if (member.getVisitFestivals().containsKey(festivalId)){
            // 값이 존재한다면
            ArrayList<Integer> missionIndexes = (ArrayList<Integer>) member.getVisitFestivals().get(festivalId).get("mission");
            missionIndexes.add(missionIndex);
        }
        else {
            List<Integer> ls = new ArrayList<>(missionIndex);
            ls.add(missionIndex);
            HashMap<String,Object> tempMap = new HashMap<>();
            tempMap.put("mission", ls);
            tempMap.put("visitDate", LocalDateTime.now());
            member.getVisitFestivals().put(festivalId, tempMap);
        }
        memberRepository.save(member);

        return member.getVisitFestivals();
    }

}
