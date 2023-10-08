package com.wanted.preonboardingbackend.jobposting.entity;

import com.wanted.preonboardingbackend.user.entity.User;
import lombok.*;

import javax.persistence.*;

@Entity(name = "applies")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Apply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "jobposting_id")
    private JobPosting jobPosting;

}
