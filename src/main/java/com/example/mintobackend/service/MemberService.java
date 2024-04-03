package com.example.mintobackend.service;

import com.example.mintobackend.entity.Member;
import com.example.mintobackend.exception.CannotFindElementException;
import com.example.mintobackend.repository.FestivalRepository;
import com.example.mintobackend.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public HashMap<String, List<Integer>> getVisitedFestival(String walletAddress){
        var member = memberRepository.findByWalletAddress(walletAddress).orElseThrow(
                ()-> new CannotFindElementException("member 가 없습니다.")
        );
        return null;
    }
    
    // member 미션 성공 -> 미션이 성공 등록 하면 자동으로 축제 기록하게 된다.
    @Transactional
    public void missionCompleted(String walletAddress, String festivalId, Integer missionIndex){
        var member = memberRepository.findByWalletAddress(walletAddress).orElseThrow(
                ()-> new CannotFindElementException("member 가 없습니다.")
        );
        // 만약 이미 방문한 축제에 값이 존재 한다면
        if (member.getVisitFestivals().containsKey(festivalId)){
            // 값이 존재한다면
//            member.getVisitFestivals().get(festivalId).add(missionIndex);
        }
        else {
            List<Integer> ls = new ArrayList<>(missionIndex);
            member.getVisitFestivals().put(festivalId, ls);
        }
    }

}
