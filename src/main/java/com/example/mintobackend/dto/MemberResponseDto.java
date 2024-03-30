package com.example.mintobackend.dto;

import com.example.mintobackend.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponseDto {
    private Integer age;
    private String area;
    private String walletAddress;
    private String gender;
    public MemberResponseDto(Member member) {
        this.age = member.getAge();
        this.area = member.getArea();
        this.walletAddress = member.getWalletAddress();
        this.gender = member.getGender();
    }

    public static MemberResponseDto of(Member member) {
        return new MemberResponseDto(member);
    }
}