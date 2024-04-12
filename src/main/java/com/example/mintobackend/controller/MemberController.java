package com.example.mintobackend.controller;

import com.example.mintobackend.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    // get 방문한 축제
    @GetMapping("/member/festival")
    public ResponseEntity<HashMap<String, Object>> getVisitedFestivals(@AuthenticationPrincipal User user){

        return null;
    }

    // post 미션 저장
    @PostMapping("/mission/complete")
    public ResponseEntity<HashMap<String, HashMap<String, Object>>> completeMission(@AuthenticationPrincipal User user, @RequestBody HashMap<String, Object> map){
        var r = memberService.missionCompleted(user.getUsername(), map.get("festivalId").toString(), Integer.valueOf(map.get("missionIndex").toString()));
        return new ResponseEntity<>(r, HttpStatus.OK);
    }
}
