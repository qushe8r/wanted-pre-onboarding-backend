package com.wanted.preonboardingbackend.company.entity;

import com.wanted.preonboardingbackend.jobposting.entity.JobPosting;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String country;

    private String city;

    @Builder.Default
    @OneToMany(mappedBy = "company",fetch = FetchType.LAZY)
    private List<JobPosting> jobPostings = new ArrayList<>();

}
