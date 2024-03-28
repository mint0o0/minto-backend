package com.example.mintobackend.repository;

import com.example.mintobackend.entity.Member;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MemberRepository extends MongoRepository<Member, String> {
    boolean existsByWalletAddress(String walletAddress);
}
