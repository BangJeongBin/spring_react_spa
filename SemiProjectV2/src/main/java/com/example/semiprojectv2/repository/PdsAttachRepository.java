package com.example.semiprojectv2.repository;

import com.example.semiprojectv2.domain.PdsAttach;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface PdsAttachRepository extends JpaRepository<PdsAttach, Long> {

    ArrayList<PdsAttach> findByPno(int pno);
}
