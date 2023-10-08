package com.wanted.preonboardingbackend.jobposting.service;

import com.wanted.preonboardingbackend.jobposting.dto.ApplyPost;
import com.wanted.preonboardingbackend.jobposting.dto.ApplyResponse;
import com.wanted.preonboardingbackend.jobposting.entity.Apply;
import com.wanted.preonboardingbackend.jobposting.entity.JobPosting;
import com.wanted.preonboardingbackend.jobposting.mapper.ApplyMapper;
import com.wanted.preonboardingbackend.jobposting.mapper.JobPostingMapper;
import com.wanted.preonboardingbackend.jobposting.repositroy.ApplyRepository;
import com.wanted.preonboardingbackend.user.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@ExtendWith(SpringExtension.class)
@Import({ApplyServiceImpl.class, ApplyMapper.class})
class ApplyServiceTest {

    @Autowired
    ApplyService applyService;

    @MockBean
    ApplyRepository applyRepository;

    @DisplayName("save: 정상 작동")
    @Test
    void save() {
        // given
        Long jobPostingId = 1L;
        Long userId = 2L;
        Long applyId = 3L;

        ApplyPost post = buildApplyPost(userId);

        Apply apply = Apply.builder().id(applyId).user(User.builder().id(userId).build()).jobPosting(JobPosting.builder().id(jobPostingId).build()).build();

        when(applyRepository.save(any(Apply.class))).thenReturn(apply);
        when(applyRepository.findByUser_IdAndJobPosting_Id(anyLong(), anyLong())).thenReturn(Optional.empty());

        // when
        ApplyResponse response = applyService.save(jobPostingId, post);

        // then
        assertThat(response.getApplyId()).isEqualTo(applyId);
        assertThat(response.getUserId()).isEqualTo(userId);
        assertThat(response.getJobPostingId()).isEqualTo(jobPostingId);
    }

    @DisplayName("save: 이미 지원하였습니다.")
    @Test
    void save_exception() {
        // given
        Long jobPostingId = 1L;
        Long userId = 2L;
        Long applyId = 3L;

        ApplyPost post = buildApplyPost(userId);

        Apply apply = Apply.builder().id(applyId).user(User.builder().id(userId).build()).jobPosting(JobPosting.builder().id(jobPostingId).build()).build();

        when(applyRepository.findByUser_IdAndJobPosting_Id(anyLong(), anyLong())).thenReturn(Optional.of(apply));

        // when & then
        assertThatThrownBy(() -> applyService.save(jobPostingId, post)).isInstanceOf(RuntimeException.class);
    }

    private ApplyPost buildApplyPost(Long userId) {
        return ApplyPost.builder().userId(userId).build();
    }
}