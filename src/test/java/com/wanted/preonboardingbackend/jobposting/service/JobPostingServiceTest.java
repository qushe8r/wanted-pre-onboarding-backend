package com.wanted.preonboardingbackend.jobposting.service;

import com.wanted.preonboardingbackend.company.entity.Company;
import com.wanted.preonboardingbackend.jobposting.dto.JobPostingPost;
import com.wanted.preonboardingbackend.jobposting.dto.JobPostingResponse;
import com.wanted.preonboardingbackend.jobposting.entity.JobPosting;
import com.wanted.preonboardingbackend.jobposting.mapper.JobPostingMapper;
import com.wanted.preonboardingbackend.jobposting.repositroy.JobPostingRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@ExtendWith(SpringExtension.class)
@Import({JobPostingServiceImpl.class, JobPostingMapper.class})
class JobPostingServiceTest {

    @Autowired
    JobPostingService jobPostingService;

    @MockBean
    JobPostingRepository jobPostingRepository;

    @DisplayName("save: 정상 작동")
    @Test
    void save() {
        // given
        Long companyId = 1L;
        Long jobPostingId = 1L;
        String position = "백엔드 주니어 개발자";
        Long hiringBonus = 1000000L;
        String skill = "Python";
        String content = "원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은...";

        JobPostingPost post = buildJobPostingPost(companyId, position, hiringBonus, skill, content);
        Company company = buildCompany(companyId, null, null, null);
        JobPosting savedJobPosting = buildJobPosting(jobPostingId, company, position, hiringBonus, skill, content);

        when(jobPostingRepository.save(any(JobPosting.class))).thenReturn(savedJobPosting);

        // when
        JobPostingResponse response = jobPostingService.save(post);

        // then
        assertThat(response.getJobPostingId()).isEqualTo(jobPostingId);
        assertThat(response.getCompanyName()).isNull();
        assertThat(response.getCountry()).isNull();
        assertThat(response.getCity()).isNull();
        assertThat(response.getPosition()).isEqualTo(position);
        assertThat(response.getHiringBonus()).isEqualTo(hiringBonus);
        assertThat(response.getSkill()).isEqualTo(skill);
        assertThat(response.getContent()).isEqualTo(content);

    }

    private Company buildCompany(Long companyId, String name, String country, String city) {
        return Company.builder().id(companyId).name(name).country(country).city(city).build();
    }

    private JobPosting buildJobPosting(Long jobPostingId, Company company, String position, Long hiringBonus, String skill, String content) {
        return JobPosting.builder().id(jobPostingId).company(company).position(position).hiringBonus(hiringBonus).skill(skill).content(content).build();
    }

    private JobPostingPost buildJobPostingPost(Long companyId, String position, Long hiringBonus, String skill, String content) {
        return JobPostingPost.builder().companyId(companyId).position(position).hiringBonus(hiringBonus).skill(skill).content(content).build();
    }

}