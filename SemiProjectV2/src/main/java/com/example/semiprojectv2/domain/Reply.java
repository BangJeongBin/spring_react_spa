package com.example.semiprojectv2.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "replys")
@Data @Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;

    @Column(nullable = false)
    private String comments;

    @Column(nullable = false)
    private String userid;

    @CreationTimestamp
    @Column(insertable = false)
    private LocalDateTime regdate;

    @Column(nullable = false)
    private int ref;

//    @Column(nullable = false, insertable = false)
//    private Long pno;

    // n:1 관계 : ManyToOne 애너테이션 사용
    //           주(n) 테이블에서 대상(1) 테이블로의 관계 설정, 외래키는 주(n)에 설정
    // JoinColumn : 외래키 컬럼 이름 지정
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pno")
    @JsonIgnore // stackOverFlow 해결책 - DTO 뱐환을 더 추천
    private Board board;
}
