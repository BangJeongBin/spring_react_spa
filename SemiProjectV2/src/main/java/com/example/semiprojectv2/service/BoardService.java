package com.example.semiprojectv2.service;

import com.example.semiprojectv2.domain.Board;
import com.example.semiprojectv2.domain.BoardListDTO;

public interface BoardService {

    Board newBoard(Board board);

    BoardListDTO readBoard(int cpg);
}
