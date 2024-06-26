package com.example.mintobackend.service;


import com.example.mintobackend.dto.MemberRequestDto;
import com.example.mintobackend.dto.MemberResponseDto;
import com.example.mintobackend.dto.TokenDto;
import com.example.mintobackend.dto.TokenRequestDto;
import com.example.mintobackend.entity.Member;
import com.example.mintobackend.entity.RefreshToken;
import com.example.mintobackend.jwt.TokenProvider;
import com.example.mintobackend.repository.MemberRepository;
import com.example.mintobackend.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final MemberRepository memberRepository;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public MemberResponseDto signup(MemberRequestDto memberRequestDto) {
        // 지갑 주소로 사용자가 존재하는 지 확인하기
        if (memberRepository.existsByWalletAddress(memberRequestDto.getWalletAddress())) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다");
        }

        Member member = memberRequestDto.toMember();
        member.setVisitFestivals(new HashMap<>());
        return MemberResponseDto.of(memberRepository.save(member));
    }

    @Transactional
    public TokenDto login(MemberRequestDto memberRequestDto) {
        if (!memberRepository.existsByWalletAddress(memberRequestDto.getWalletAddress())) {
            throw new RuntimeException("회원가입이 필요합니다");
        }
        
        // 지갑 주소만 기반으로 jwt 생성 -> customAuthenticationProvider 통해서 설정
        Authentication authenticationToken = authenticationManagerBuilder.getObject().authenticate(new UsernamePasswordAuthenticationToken(memberRequestDto.getWalletAddress(), null));
//        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(memberRequestDto.getWalletAddress(), null);
        // 1. 지갑주소를 통해서만 authenticate check!
//        UsernamePasswordAuthenticationToken authenticationToken = memberRequestDto.toAuthentication();
        // 2. 실제로 검증 (사용자 비밀번호 체크) 이 이루어지는 부분 // 안함
        //    authenticate 메서드가 실행이 될 때 CustomUserDetailsService 에서 만들었던 loadUserByUsername 메서드가 실행됨
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenDto tokenDto = tokenProvider.generateTokenDto(authenticationToken);
        // 4. RefreshToken 저장
        RefreshToken refreshToken = RefreshToken.builder()
                .key(authentication.getName())
                .value(tokenDto.getRefreshToken())
                .build();

        refreshTokenRepository.save(refreshToken);

        // 5. 토큰 발급
        return tokenDto;
    }

    @Transactional
    public TokenDto reissue(TokenRequestDto tokenRequestDto) {
        // 1. Refresh Token 검증
        if (!tokenProvider.validateToken(tokenRequestDto.getRefreshToken())) {
            throw new RuntimeException("Refresh Token 이 유효하지 않습니다.");
        }

        // 2. Access Token 에서 Member ID 가져오기
        Authentication authentication = tokenProvider.getAuthentication(tokenRequestDto.getAccessToken());

        // 3. 저장소에서 Member ID 를 기반으로 Refresh Token 값 가져옴
        RefreshToken refreshToken = refreshTokenRepository.findByRtKey(authentication.getName())
                .orElseThrow(() -> new RuntimeException("로그아웃 된 사용자입니다."));
        // 4. Refresh Token 일치하는지 검사
        if (!refreshToken.getRtValue().equals(tokenRequestDto.getRefreshToken())) {
            throw new RuntimeException("토큰의 유저 정보가 일치하지 않습니다.");
        }

        // 5. 새로운 토큰 생성
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        // 6. 저장소 정보 업데이트
        RefreshToken newRefreshToken = refreshToken.updateValue(tokenDto.getRefreshToken());
        refreshTokenRepository.save(newRefreshToken);

        // 토큰 발급
        return tokenDto;
    }

    @Transactional(readOnly = true)
    public boolean isExistMemberByWalletAddress(String walletAddress){
        return memberRepository.existsByWalletAddress(walletAddress);
    }

    @Transactional
    public void withdrawal(String userId) {
        memberRepository.deleteById(userId);
    }
}