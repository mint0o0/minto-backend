package com.example.mintobackend.service;

import com.example.mintobackend.dto.VisitFestivalDto;
import com.example.mintobackend.exception.CannotFindElementException;
import com.example.mintobackend.repository.FestivalRepository;
import com.example.mintobackend.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
    @Transactional(readOnly = true)
    public Object getCompleteMission(String memberId, String festivalId){
        var member = memberRepository.findByWalletAddress(memberId).orElseThrow(
                ()->new RuntimeException("멤버 못찾음")
        );
        var festival = festivalRepository.findById(festivalId).orElseThrow(
                () ->new RuntimeException("축제 못찾음")
        );
        return member.getVisitFestivals().get(festivalId);
    }

    @Transactional(readOnly = true)
    public Integer checkCompleteMission(String memberId, String festivalId){
        var member = memberRepository.findByWalletAddress(memberId).orElseThrow(
                ()->new RuntimeException("멤버 못찾음")
        );
        var festival = festivalRepository.findById(festivalId).orElseThrow(
                () ->new RuntimeException("축제 못찾음")
        );
        List<Integer> memberCompleteMission = (List<Integer>) member.getVisitFestivals().get(festivalId).get("mission");
        var festivalMission = festival.getMissions();
        // 시작 안했으면 0
        // 전부 다 완료하지는 않았고 하나라도 완료 했으면 1
        // 전부 다 완료하면 2
        System.out.println(memberCompleteMission);
        System.out.println(festivalMission);
        if (memberCompleteMission.isEmpty()){
            return 0;
        }
        else if (memberCompleteMission.size() == festivalMission.size()){
            return 2;
        }
        else {
            return 1;
        }

    }

    @Transactional(readOnly = true)
    public Object getVisitFestivalByMonth(String memberId, Integer month){
        // 모든 축제 다 가져오기
        var member = memberRepository.findByWalletAddress(memberId).orElseThrow(
                ()->new RuntimeException("멤버 못찾음")
        );
        var response = new HashMap<LocalDate, List<Object>>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
        var visitFestival = member.getVisitFestivals();
        System.out.println(visitFestival);
        for (String festivalId: visitFestival.keySet()){
            var festival = festivalRepository.findById(festivalId).orElseThrow(
                    () -> new RuntimeException("축제 없음")
            );

            System.out.println(visitFestival.get(festivalId).get("visitDate"));
            var s =  visitFestival.get(festivalId).get("visitDate").toString();
            LocalDateTime dateTime = LocalDateTime.parse(s, formatter);

            // 월에 맞으면 추가
            if (dateTime.getMonth().getValue() == month){
                var x = response.getOrDefault(dateTime.toLocalDate(), new ArrayList<>());
                x.add(festival);
                response.put(dateTime.toLocalDate(), x);
            }

        }
        return response;
    }
}
