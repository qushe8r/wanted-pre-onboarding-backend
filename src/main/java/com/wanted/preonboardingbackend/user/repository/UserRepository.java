package com.wanted.preonboardingbackend.user.repository;

import com.wanted.preonboardingbackend.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
