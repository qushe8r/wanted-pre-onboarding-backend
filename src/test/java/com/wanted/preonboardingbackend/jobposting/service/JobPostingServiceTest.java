package com.wanted.preonboardingbackend.jobposting.service;

import com.wanted.preonboardingbackend.company.entity.Company;
import com.wanted.preonboardingbackend.jobposting.dto.JobPostingPatch;
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

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
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

    @DisplayName("getPosts: searchCond가 null이어도 정상 작동함")
    @Test
    void getPosts_searchCondEqNull() {
        // given
        Long companyId = 1L;
        String name = "원티드랩";
        String country = "대한민국";
        String city = "서울";

        Company company = buildCompany(companyId, name, country, city);

        Long jobPostingId = 1L;
        String position = "백엔드 주니어 개발자";
        Long hiringBonus = 1000000L;
        String skill = "Python";
        String content = "원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은...";

        JobPosting jobPosting = buildJobPosting(jobPostingId, company, position, hiringBonus, skill, content);

        String searchCond = null;
        when(jobPostingRepository.search(searchCond)).thenReturn(List.of(jobPosting));

        // when
        List<JobPostingResponse> responses = jobPostingService.getPosts(searchCond);
        JobPostingResponse response = responses.get(0);

        // then
        assertThat(response.getJobPostingId()).isEqualTo(jobPostingId);
        assertThat(response.getCompanyName()).isEqualTo(name);
        assertThat(response.getCountry()).isEqualTo(country);
        assertThat(response.getCity()).isEqualTo(city);
        assertThat(response.getPosition()).isEqualTo(position);
        assertThat(response.getHiringBonus()).isEqualTo(hiringBonus);
        assertThat(response.getSkill()).isEqualTo(skill);
        assertThat(response.getContent()).isEqualTo(content);
    }

    @DisplayName("getPosts: searchCond가 null이어도 정상 작동함")
    @Test
    void getPosts_searchCondNotNull() {
        // given
        Long companyId = 1L;
        String name = "원티드랩";
        String country = "대한민국";
        String city = "서울";

        Company company = buildCompany(companyId, name, country, city);

        Long jobPostingId = 1L;
        String position = "백엔드 주니어 개발자";
        Long hiringBonus = 1000000L;
        String skill = "Python";
        String content = "원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은...";

        JobPosting jobPosting = buildJobPosting(jobPostingId, company, position, hiringBonus, skill, content);

        String searchCond = name;
        when(jobPostingRepository.search(searchCond)).thenReturn(List.of(jobPosting));

        // when
        List<JobPostingResponse> responses = jobPostingService.getPosts(searchCond);
        JobPostingResponse response = responses.get(0);

        // then
        assertThat(response.getJobPostingId()).isEqualTo(jobPostingId);
        assertThat(response.getCompanyName()).isEqualTo(name);
        assertThat(response.getCountry()).isEqualTo(country);
        assertThat(response.getCity()).isEqualTo(city);
        assertThat(response.getPosition()).isEqualTo(position);
        assertThat(response.getHiringBonus()).isEqualTo(hiringBonus);
        assertThat(response.getSkill()).isEqualTo(skill);
        assertThat(response.getContent()).isEqualTo(content);
    }

    @DisplayName("update: 정상 작동")
    @Test
    void update() {
        // given
        Long jobPostingId = 1L;

        String patchPosition = "백엔드 주니어 개발자";
        Long patchHiringBonus = 1000000L;
        String patchSkill = "Python";
        String patchContent = "원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은...";

        JobPostingPatch patch = buildJobPostingPatch(patchPosition, patchHiringBonus, patchSkill, patchContent);

        Long companyId = 1L;
        String name = "원티드랩";
        String country = "대한민국";
        String city = "서울";

        Company company = buildCompany(companyId, name, country, city);

        String position = "백엔드 시니어 개발자";
        Long hiringBonus = 3000000L;
        String skill = "Java";
        String content = "원티드랩에서 백엔드 시니어 개발자를 채용합니다. 자격요건은 ...";


        JobPosting jobPosting = buildJobPosting(jobPostingId, company, position, hiringBonus, skill, content);

        when(jobPostingRepository.findById(anyLong())).thenReturn(Optional.of(jobPosting));

        // when
        JobPostingResponse response = jobPostingService.update(jobPostingId, patch);

        // then
        assertThat(response.getJobPostingId()).isEqualTo(jobPostingId);
        assertThat(response.getCompanyName()).isEqualTo(name);
        assertThat(response.getCountry()).isEqualTo(country);
        assertThat(response.getCity()).isEqualTo(city);
        assertThat(response.getPosition()).isEqualTo(patchPosition);
        assertThat(response.getHiringBonus()).isEqualTo(patchHiringBonus);
        assertThat(response.getSkill()).isEqualTo(patchSkill);
        assertThat(response.getContent()).isEqualTo(patchContent);
    }

    @DisplayName("update: jobPostingRepository.findById() 가 Optional.ofNullable(null)을 리턴")
    @Test
    void update_repositoryReturnOfNullable() {
        // given
        Long jobPostingId = 1L;

        String position = "백엔드 주니어 개발자";
        Long hiringBonus = 1000000L;
        String skill = "Python";
        String content = "원티드랩에서 백엔드 주니어 개발자를 채용합니다. 자격요건은...";

        JobPostingPatch patch = buildJobPostingPatch(position, hiringBonus, skill, content);

        JobPosting jobPosting = null;

        when(jobPostingRepository.findById(anyLong())).thenReturn(Optional.ofNullable(jobPosting));

        // when & then
        assertThatThrownBy(() -> jobPostingService.update(jobPostingId, patch))
                .isInstanceOf(RuntimeException.class);

    }

    private Company buildCompany(Long companyId, String name, String country, String city) {
        return Company.builder().id(companyId).name(name).country(country).city(city).build();
    }

    private JobPosting buildJobPosting(Long jobPostingId, Company company, String position, Long hiringBonus, String skill, String content) {
        return JobPosting.builder().id(jobPostingId).company(company).position(position).hiringBonus(hiringBonus).skill(skill).content(content).build();
    }

    private JobPostingPatch buildJobPostingPatch(String position, Long hiringBonus, String skill, String content) {
        return JobPostingPatch.builder().position(position).hiringBonus(hiringBonus).skill(skill).content(content).build();
    }

    private JobPostingPost buildJobPostingPost(Long companyId, String position, Long hiringBonus, String skill, String content) {
        return JobPostingPost.builder().companyId(companyId).position(position).hiringBonus(hiringBonus).skill(skill).content(content).build();
    }

}