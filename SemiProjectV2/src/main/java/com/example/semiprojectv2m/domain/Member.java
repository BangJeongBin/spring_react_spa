package com.example.semiprojectv2m.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data   // setter, getter, toString 자동 생성
@Builder
public class Member {

    private int mno;
    private String userid;
    private String passwd;
    private String name;
    private String email;
    private LocalDateTime regdate;
}
