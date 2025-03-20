package com.example.semiprojectv2m.service;

import com.example.semiprojectv2m.domain.User;
import com.example.semiprojectv2m.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userMapper;


    @Override
    public User newUser(User user) {
        // 아이디 중복 체크
        if (userMapper.existsByUserid(user.getUserid())) {
            throw new IllegalStateException("이미 존재하는 아이디입니다.");
        }

        // 이메일 중복 체크
        if (userMapper.existsByEmail(user.getEmail())) {
            throw new IllegalStateException("이미 존재하는 이메일입니다.");
        }
        return userMapper.save(user);
    }

    @Override
    public User loginUser(User user) {
        return null;
    }
}
