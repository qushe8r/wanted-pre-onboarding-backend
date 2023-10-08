package com.wanted.preonboardingbackend.jobposting.repositroy;

import com.wanted.preonboardingbackend.jobposting.entity.Apply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplyRepository extends JpaRepository<Apply, Long> {

    Optional<Apply> findByUser_IdAndJobPosting_Id(Long userId, Long jobPostingId);
}
