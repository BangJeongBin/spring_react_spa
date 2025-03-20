package com.example.semiprojectv2m.controller;

import com.example.semiprojectv2m.domain.Board;
import com.example.semiprojectv2m.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins="http://localhost:5173/")
@Slf4j
@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/write")
    public ResponseEntity<?> writeok(@RequestBody Board board) {
        log.info("submit 된 게시판 데이터 : {}", board);

        ResponseEntity<?> response = ResponseEntity.internalServerError().build();

        try {
            boardService.newBoard(board);
            response = ResponseEntity.ok().build();
        } catch (IllegalStateException ex) {
            response = ResponseEntity.badRequest().body(ex.getMessage());
        }
        return response;
    }
}
