package com.example.semiprojectv2.domain;

import lombok.Data;

import java.util.List;

@Data
public class BoardReplyDTO {
    private Board bd;
    private List<?> rps;

    public BoardReplyDTO(Board bd, List<?> rps) {
        this.bd = bd;
        this.rps = rps;
    }
}
