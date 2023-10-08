package com.wanted.preonboardingbackend.user.service;

import com.wanted.preonboardingbackend.user.dto.UserResponse;
import com.wanted.preonboardingbackend.user.entity.User;
import com.wanted.preonboardingbackend.user.mapper.UserMapper;
import com.wanted.preonboardingbackend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponse save() {
        User user = User.builder().build();
        User saved = userRepository.save(user);
        return userMapper.toResponse(saved);
    }

}

