package com.example.mintobackend.repository;

import com.example.mintobackend.entity.Member;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface MemberRepository extends MongoRepository<Member, String> {
    boolean existsByWalletAddress(String walletAddress);
    
    // member 정보 가져 오기
    Optional<Member> findByWalletAddress(String walletAddress);
}
