package com.example.semiprojectv2m.repository;

import com.example.semiprojectv2m.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUserid(String userid);
    boolean existsByEmail(String email);
    User findByUserid(String userid);
}
