package com.example.semiprojectv2.service;

import com.example.semiprojectv2.domain.Board;
import com.example.semiprojectv2.domain.BoardDTO;
import com.example.semiprojectv2.domain.BoardListDTO;
import com.example.semiprojectv2.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardMapper;
    @Value("${board.pagesize}") private int pageSize;


    @Override
    public Board newBoard(Board board) {

        return boardMapper.save(board);
    }


    @Override
    public BoardListDTO readBoard(int cpg) {
        int stnum = (cpg - 1) * pageSize;
        int totalItems = (int)boardMapper.count(); // JPA 자동 생성
        List<BoardDTO> boards = boardMapper.findBoards(stnum, pageSize);

        return new BoardListDTO(cpg, totalItems, pageSize, boards);
    }
}
