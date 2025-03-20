package com.example.semiprojectv2m.repository;

import com.example.semiprojectv2m.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {

}
