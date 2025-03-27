package com.example.semiprojectv2.service;

import com.example.semiprojectv2.domain.Board;
import com.example.semiprojectv2.domain.BoardDTO;
import com.example.semiprojectv2.domain.BoardListDTO;
import com.example.semiprojectv2.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
        Pageable pageable = PageRequest.of(cpg, pageSize, Sort.Direction.DESC, "bno");

        Page<BoardDTO> pageBoards = boardMapper.findBy(pageable);
        List<BoardDTO> boards = pageBoards.getContent();
        int totalItems = (int) pageBoards.getTotalElements();
        int cntpg = pageBoards.getTotalPages();

        return new BoardListDTO(cpg, totalItems, pageSize, boards);
    }
}
