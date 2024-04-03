package com.example.mintobackend.controller;

import com.example.mintobackend.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    public ResponseEntity getVisitedFestivals(@AuthenticationPrincipal User user){
        return null;
    }

}
