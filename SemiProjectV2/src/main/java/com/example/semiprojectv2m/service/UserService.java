package com.example.semiprojectv2m.service;

import com.example.semiprojectv2m.domain.User;

public interface UserService {

    User newUser(User user);

    User loginUser(User user);
}
