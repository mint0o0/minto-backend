package com.example.mintobackend.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.annotation.Collation;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@NoArgsConstructor
@Document(collection = "refreshToken")

public class RefreshToken {

    @Id
    private String rtKey;

    private String rtValue;

    @Builder
    public RefreshToken(String key, String value) {
        this.rtKey = key;
        this.rtValue = value;
    }

    public RefreshToken updateValue(String token) {
        this.rtValue = token;
        return this;
    }
}