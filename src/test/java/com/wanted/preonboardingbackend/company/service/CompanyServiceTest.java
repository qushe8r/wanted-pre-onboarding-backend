package com.wanted.preonboardingbackend.company.service;

import com.wanted.preonboardingbackend.company.dto.CompanyPost;
import com.wanted.preonboardingbackend.company.dto.CompanyResponse;
import com.wanted.preonboardingbackend.company.entity.Company;
import com.wanted.preonboardingbackend.company.mapper.CompanyMapper;
import com.wanted.preonboardingbackend.company.repository.CompanyRepository;
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
@Import({CompanyServiceImpl.class, CompanyMapper.class})
class CompanyServiceTest {

    @Autowired
    CompanyService companyService;

    @MockBean
    CompanyRepository companyRepository;

    @Test
    void save() {
        // given
        Long companyId = 1L;
        String country = "대한민국";
        String city = "서울";
        String name = "원티드랩";

        CompanyPost post = CompanyPost.builder().name(name).country(country).city(city).build();
        Company findCompany = Company.builder().id(companyId).name(name).country(country).city(city).build();

        when(companyRepository.save(Mockito.any(Company.class))).thenReturn(findCompany);

        // when
        CompanyResponse response = companyService.save(post);

        // then
        Assertions.assertThat(response.getCompanyId()).isEqualTo(companyId);
        Assertions.assertThat(response.getName()).isEqualTo(name);
        Assertions.assertThat(response.getCountry()).isEqualTo(country);
        Assertions.assertThat(response.getCity()).isEqualTo(city);
    }

}