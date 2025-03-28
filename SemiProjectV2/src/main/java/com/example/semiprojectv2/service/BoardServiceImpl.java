package com.example.semiprojectv2.service;

import com.example.semiprojectv2.domain.*;
import com.example.semiprojectv2.repository.BoardRepository;
import com.example.semiprojectv2.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardMapper;
    private final ReplyRepository replyMapper;
    @Value("${board.pagesize}")
    private int pageSize;


    @Override
    public Board newBoard(Board board) {

        return boardMapper.save(board);
    }


    @Override
    public BoardListDTO readBoard(int cpg) {
        Pageable pageable = PageRequest.of(cpg - 1, pageSize, Sort.Direction.DESC, "bno");

        Page<BoardDTO> pageBoards = boardMapper.findBy(pageable);
        List<BoardDTO> boards = pageBoards.getContent();
        int totalItems = (int) pageBoards.getTotalElements();
        int cntpg = pageBoards.getTotalPages();

        return new BoardListDTO(cpg, totalItems, pageSize, boards);
    }


    @Override
    public BoardListDTO findBoard(int cpg, String findtype, String findkey) {
        Pageable pageable = PageRequest.of(cpg - 1, pageSize, Sort.Direction.DESC, "bno");
        Page<BoardDTO> pageBoards = null;

        switch (findtype) {
            case "title":
                pageBoards = boardMapper.findByTitleContains(pageable, findkey);
                break;
            case "userid":
                pageBoards = boardMapper.findByUseridContains(pageable, findkey);
                break;
            case "contents":
                pageBoards = boardMapper.findByContentsContains(pageable, findkey);
                break;
            case "titconts": // 매개변수 2개를 넘겨주어야 정상적으로 작동
                pageBoards = boardMapper.findByTitleContainsOrContentsContains(pageable, findkey, findkey);
                break;
        }

        List<BoardDTO> boards = pageBoards.getContent();
        int totalItems = (int) pageBoards.getTotalElements();

        return new BoardListDTO(cpg, totalItems, pageSize, boards);
    }


    @Transactional
    @Override
    public BoardReplyDTO readOneBoardReply(Long bno) {
        boardMapper.updateViews(bno);
        Board board = boardMapper.findByBno(bno);
//        List<Reply> replies = replyMapper.findByPnoOrderByRef(bno);

//        return new BoardReplyDTO(board, replies);
        return new BoardReplyDTO(board, board.getReplies());
    }


    @Override
    public BoardReplyDTO readOneBoardWithReply(Long bno) {
        //Board boardreply =  boardMapper.findByBnoWithReply(bno);
        Board boardreply = boardMapper.findByBno(bno);

        return new BoardReplyDTO(boardreply, boardreply.getReplies());
    }


    @Override
    public Page<BoardDTO> testReadBoard(int cpg) {
        Pageable pageable = PageRequest.of(cpg, pageSize, Sort.Direction.DESC, "bno");

        Page<BoardDTO> pageBoards = boardMapper.findBy(pageable);

        return pageBoards;
    }
}
