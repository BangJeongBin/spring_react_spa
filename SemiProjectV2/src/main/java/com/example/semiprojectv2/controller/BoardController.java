package com.example.semiprojectv2.controller;

import com.example.semiprojectv2.domain.Board;
import com.example.semiprojectv2.domain.BoardListDTO;
import com.example.semiprojectv2.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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


    // list 엔드포인트 변경
    // 변경 전 : http://localhost:8080/api/board/list?cpg=1
    // 변경 후 : http://localhost:8080/api/board/list/1
    @GetMapping("/list/{cpg}")
    public ResponseEntity<?> list(@PathVariable int cpg) {
        BoardListDTO boardListDTO = boardService.readBoard(cpg);

        return new ResponseEntity<>(boardListDTO, HttpStatus.OK);
    }
}
