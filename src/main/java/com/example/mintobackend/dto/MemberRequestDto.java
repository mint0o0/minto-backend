package com.example.mintobackend.dto;

import com.example.mintobackend.entity.Authority;
import com.example.mintobackend.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashMap;


@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MemberRequestDto {
    private String walletAddress;
    private String area;
    private Integer age;
    private String gender;
    public Member toMember() {
        return Member.builder()
                .area(area)
                .walletAddress(walletAddress)
                .age(age)
                .gender(gender)
                .authority(Authority.ROLE_USER)
                .build();
    }

//    public UsernamePasswordAuthenticationToken toAuthentication() {
//        System.out.println(walletAddress);
//        return new UsernamePasswordAuthenticationToken(walletAddress, null, AuthorityUtils.createAuthorityList(Authority.ROLE_USER.toString()));
//    }
}