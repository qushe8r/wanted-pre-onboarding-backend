package com.wanted.preonboardingbackend.user.entity;

import com.wanted.preonboardingbackend.jobposting.entity.Apply;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "users")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Builder.Default
    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private List<Apply> applies = new ArrayList<>();

}
