package com.wanted.preonboardingbackend.user.service;

import com.wanted.preonboardingbackend.user.dto.UserResponse;
import com.wanted.preonboardingbackend.user.entity.User;
import com.wanted.preonboardingbackend.user.mapper.UserMapper;
import com.wanted.preonboardingbackend.user.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.when;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@ExtendWith(SpringExtension.class)
@Import({UserServiceImpl.class, UserMapper.class})
class UserServiceTest {

    @Autowired
    UserService userService;

    @MockBean
    UserRepository userRepository;

    @Test
    void save() {
        // given
        Long userId = 1L;
        User findUser = User.builder().id(userId).build();

        // when
        when(userRepository.save(Mockito.any(User.class))).thenReturn(findUser);
        UserResponse response = userService.save();

        // then
        Assertions.assertThat(response.getUserId()).isEqualTo(userId);
    }

}