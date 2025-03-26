package com.example.semiprojectv2.repository;

import com.example.semiprojectv2.domain.Board;
import com.example.semiprojectv2.domain.BoardDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    @Query(value = "select bno, title, userid, regdate, views, thumbs from boards order by bno desc limit :stnum, :pageSize",
            nativeQuery = true)
    List<BoardDTO> findBoards(int stnum, int pageSize);
}
