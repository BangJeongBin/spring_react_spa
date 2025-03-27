package com.example.semiprojectv2.repository;

import com.example.semiprojectv2.domain.Board;
import com.example.semiprojectv2.domain.BoardDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;



public interface BoardRepository extends JpaRepository<Board, Long> {

//    @Query(value = "select bno, title, userid, regdate, views, thumbs from boards order by bno desc limit :stnum, :pageSize",
//            nativeQuery = true)
//    List<BoardDTO> findBoards(int stnum, int pageSize);

    Page<BoardDTO> findBy(Pageable pageable); // JPA 자동 페이지네이션 - 데이터베이스에 상관없이 sql문 자동 생성

    Page<BoardDTO> findByTitleContains(Pageable pageable, String findkey);
    Page<BoardDTO> findByUseridContains(Pageable pageable, String findkey);
    Page<BoardDTO> findByContentsContains(Pageable pageable, String findkey);

    Page<BoardDTO> findByTitleContainsOrContentsContains(Pageable pageable, String fkey1, String fkey2);
}
