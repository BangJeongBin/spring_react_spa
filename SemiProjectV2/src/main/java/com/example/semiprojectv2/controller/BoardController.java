package com.example.semiprojectv2.controller;

import com.example.semiprojectv2.domain.Board;
import com.example.semiprojectv2.domain.BoardDTO;
import com.example.semiprojectv2.domain.BoardListDTO;
import com.example.semiprojectv2.domain.BoardReplyDTO;
import com.example.semiprojectv2.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins= {"http://localhost:5173", "http://localhost:3001"})
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
    // 변경 전 : http://localhost:8080/api/board/list?cpg=4
    // 변경 후 : http://localhost:8080/api/board/list/4
    @GetMapping("/list/{cpg}")
    public ResponseEntity<?> list(@PathVariable int cpg) {
        BoardListDTO boardListDTO = boardService.readBoard(cpg);

        return new ResponseEntity<>(boardListDTO, HttpStatus.OK);
    }


    @GetMapping("/find/{findtype}/{findkey}/{cpg}")
    public ResponseEntity<?> find(@PathVariable int cpg, @PathVariable String findtype, @PathVariable String findkey) {
        BoardListDTO boardListDTO = boardService.findBoard(cpg, findtype, findkey);

        return new ResponseEntity<>(boardListDTO, HttpStatus.OK);
    }


    @GetMapping("/view/{bno}")
    public ResponseEntity<?> view(@PathVariable Long bno) {
        //BoardReplyDTO boardReply = boardService.readOneBoardReply(bno);
        BoardReplyDTO boardReply = boardService.readOneBoardWithReply(bno);

        return new ResponseEntity<>(boardReply, HttpStatus.OK);
    }


    @GetMapping("/test/{cpg}")
    public ResponseEntity<?> test(@PathVariable int cpg) {
        Page<BoardDTO> pageBoards = boardService.testReadBoard(cpg);

        return new ResponseEntity<>(pageBoards, HttpStatus.OK);
    }
}
