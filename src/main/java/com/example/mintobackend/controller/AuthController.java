package com.example.mintobackend.controller;

import com.example.mintobackend.dto.MemberRequestDto;
import com.example.mintobackend.dto.MemberResponseDto;
import com.example.mintobackend.dto.TokenDto;
import com.example.mintobackend.dto.TokenRequestDto;
import com.example.mintobackend.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController  {
    private final AuthService authService;
    @PostMapping("/signup")
    public ResponseEntity<MemberResponseDto> signup(@RequestBody MemberRequestDto memberRequestDto) {
        return ResponseEntity.ok(authService.signup(memberRequestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody MemberRequestDto memberRequestDto) {
        return ResponseEntity.ok(authService.login(memberRequestDto));
    }

    @PostMapping("/reissue")
    public ResponseEntity<TokenDto> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
        return ResponseEntity.ok(authService.reissue(tokenRequestDto));
    }

    @DeleteMapping("/withdrawal")
    public ResponseEntity<String> withdrawal(@AuthenticationPrincipal User user) {
        authService.withdrawal(user.getUsername());
        return ResponseEntity.ok("deleted");
    }

    @GetMapping("/exist/{walletAddress}")
    public ResponseEntity<Boolean> isExistMember(@PathVariable String walletAddress){
        return new ResponseEntity<>(authService.isExistMemberByWalletAddress(walletAddress), HttpStatus.OK) ;
    }
}
