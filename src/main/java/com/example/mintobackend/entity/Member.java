package com.example.mintobackend.entity;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.List;

@Document(collection = "member")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class Member {
    @Id
    private String id;
    private String walletAddress;
    private Integer age;
    private String area;
    private String gender;

    // enum type
    private Authority authority;

    // todo: 어떻게 할지 좀더 생각
    // festivalId: {visitDate: 2020-10-15, mission: [1, 3, 2]}
    private HashMap<String, Object> visitFestivals;
}
