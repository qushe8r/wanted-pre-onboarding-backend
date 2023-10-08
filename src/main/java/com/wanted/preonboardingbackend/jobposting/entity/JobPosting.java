package com.wanted.preonboardingbackend.jobposting.entity;

import com.wanted.preonboardingbackend.company.entity.Company;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Getter
@Builder
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class JobPosting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String position;

    private Long hiringBonus;

    private String content;

    private String skill;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @Builder.Default
    @OneToMany(mappedBy = "jobPosting", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Apply> applies = new ArrayList<>();

    public void update(String position, Long hiringBonus, String content, String skill) {
        Optional.ofNullable(position).ifPresent(this::setPosition);
        Optional.ofNullable(hiringBonus).ifPresent(this::setHiringBonus);
        Optional.ofNullable(content).ifPresent(this::setContent);
        Optional.ofNullable(skill).ifPresent(this::setSkill);
    }

}
