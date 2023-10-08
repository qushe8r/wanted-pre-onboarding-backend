package com.wanted.preonboardingbackend.jobposting.repositroy.querydsl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wanted.preonboardingbackend.jobposting.entity.JobPosting;

import javax.persistence.EntityManager;
import java.util.List;

import static com.wanted.preonboardingbackend.company.entity.QCompany.company;
import static com.wanted.preonboardingbackend.jobposting.entity.QJobPosting.jobPosting;
import static org.springframework.util.StringUtils.hasText;

public class JobPostingRepositoryImpl implements JobPostingRepositoryQueryDSL {

    private final JPAQueryFactory queryFactory;

    public JobPostingRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<JobPosting> search(String searchCond) {
        BooleanBuilder builder = new BooleanBuilder();
        return queryFactory
                .selectFrom(jobPosting)
                .join(jobPosting.company, company)
                .fetchJoin()
                .where(builder
                        .or(positionContains(searchCond))
                        .or(skillEq(searchCond))
                        .or(countryContains(searchCond))
                        .or(cityContains(searchCond))
                        .or(nameContains(searchCond)))
                .orderBy(jobPosting.id.desc())
                .fetch();
    }

    private BooleanExpression nameContains(String searchCond) {
        return hasText(searchCond) ? company.name.contains(searchCond) : null;
    }

    private BooleanExpression cityContains(String searchCond) {
        return hasText(searchCond) ? company.city.contains(searchCond) : null;
    }

    private BooleanExpression countryContains(String searchCond) {
        return hasText(searchCond) ? company.country.contains(searchCond) : null;
    }

    private BooleanExpression skillEq(String searchCond) {
        return hasText(searchCond) ? jobPosting.skill.eq(searchCond) : null;
    }

    private BooleanExpression positionContains(String searchCond) {
        return hasText(searchCond) ? jobPosting.position.contains(searchCond) : null;
    }

}
