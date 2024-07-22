package com.ata.be.repository;

import com.ata.be.dto.JobDataRequest;
import com.ata.be.entity.QSalarySurveyEntity;
import com.ata.be.entity.SalarySurveyEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.ata.be.entity.QSalarySurveyEntity.salarySurveyEntity;

@Repository
public class SearchJobDataRepository extends QuerydslRepositorySupport {

    public SearchJobDataRepository() {
        super(SalarySurveyEntity.class);
    }

    public PageImpl<SalarySurveyEntity> searchJobData(JobDataRequest jobDataRequest, Pageable pageable) {
        QSalarySurveyEntity salarySurveyEntity = QSalarySurveyEntity.salarySurveyEntity;
        Querydsl querydsl = getQuerydsl();

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        Optional.ofNullable(jobDataRequest.getJobTitle()).ifPresent(item -> booleanBuilder.and(salarySurveyEntity.jobTitle.contains(item)));
        Optional.ofNullable(jobDataRequest.getGender()).ifPresent(item -> booleanBuilder.and(salarySurveyEntity.gender.contains(item)));

        switch (jobDataRequest.getSalaryOperator()) {
            case GT:
                Optional.ofNullable(jobDataRequest.getSalary()).ifPresent(item -> booleanBuilder.and(salarySurveyEntity.salary.gt(item)));
                break;
            case GOE:
                Optional.ofNullable(jobDataRequest.getSalary()).ifPresent(item -> booleanBuilder.and(salarySurveyEntity.salary.goe(item)));
                break;
            case LT:
                Optional.ofNullable(jobDataRequest.getSalary()).ifPresent(item -> booleanBuilder.and(salarySurveyEntity.salary.lt(item)));
                break;
            case LOE:
                Optional.ofNullable(jobDataRequest.getSalary()).ifPresent(item -> booleanBuilder.and(salarySurveyEntity.salary.loe(item)));
                break;
            default:
                Optional.ofNullable(jobDataRequest.getSalary()).ifPresent(item -> booleanBuilder.and(salarySurveyEntity.salary.contains(item)));
                break;
        }


        Expression<?>[] selectedFiled = getSelectedFiled(jobDataRequest.getField());

        JPQLQuery<SalarySurveyEntity> query = Objects.requireNonNull(querydsl).createQuery()
                .select(Projections.fields(SalarySurveyEntity.class, selectedFiled))
                .from(salarySurveyEntity)
                .where(booleanBuilder);

        JPQLQuery<SalarySurveyEntity> pagedQuery = querydsl.applyPagination(pageable, query);
        return new PageImpl<>(pagedQuery.fetch(), pageable, pagedQuery.fetchCount());
    }

    private Expression<?>[] getSelectedFiled(List<String> selectedField) {
        Field[] fields = SalarySurveyEntity.class.getFields();

        if (ObjectUtils.isEmpty(selectedField)) {
            selectedField = Arrays.stream(fields).map(Field::getName).toList();
        } else {
            selectedField = selectedField.stream().filter(fieldName ->
                    Arrays.stream(fields).anyMatch(field -> field.getName().equals(fieldName))).toList();
        }

        return selectedField.stream()
                .map(fieldName -> {
                    try {
                        return Expressions.path(SalarySurveyEntity.class.getField(fieldName).getType(),
                                salarySurveyEntity, fieldName);
                    } catch (NoSuchFieldException e) {
                        throw new RuntimeException(e);
                    }
                }).toArray(Expression[]::new);
    }
}
