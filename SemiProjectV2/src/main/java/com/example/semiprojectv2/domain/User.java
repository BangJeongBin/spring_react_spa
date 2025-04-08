package com.example.semiprojectv2.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users4")
@Data @Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mno;

    @Column(unique=true, nullable=false)
    private String userid;

    @Column(nullable=false)
    private String passwd;

    @Column(nullable=false)
    private String name;

    @Column(unique=true, nullable=false)
    private String email;

    @Column
    private String enabled = "false";

    @Column
    private String verifycode;

    @Column
    private String role = "USER";

    @CreationTimestamp
    private LocalDateTime regdate;

    @Transient // 엔티티 컬럼과 무관한 변수로 선언, DB에서 접근하지 않음
    @JsonProperty("g-recaptcha-response")
    private String gRecaptchaResponse;

}
