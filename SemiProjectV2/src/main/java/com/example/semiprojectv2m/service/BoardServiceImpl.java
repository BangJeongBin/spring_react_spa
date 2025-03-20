package com.example.semiprojectv2m.service;

import com.example.semiprojectv2m.domain.Board;
import com.example.semiprojectv2m.domain.User;
import com.example.semiprojectv2m.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardMapper;


    @Override
    public Board newBoard(Board board) {

        return boardMapper.save(board);
    }
}
