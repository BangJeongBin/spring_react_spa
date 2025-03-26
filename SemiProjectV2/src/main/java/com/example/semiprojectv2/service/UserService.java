package com.example.semiprojectv2.service;

import com.example.semiprojectv2.domain.User;

public interface UserService {

    User newUser(User user);

    User loginUser(User user);
}
