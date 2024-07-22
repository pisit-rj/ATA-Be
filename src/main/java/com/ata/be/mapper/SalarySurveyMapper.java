package com.ata.be.mapper;

import com.ata.be.dto.JobDataResponse;
import com.ata.be.entity.SalarySurveyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SalarySurveyMapper {
    SalarySurveyMapper INSTANCE = Mappers.getMapper(SalarySurveyMapper.class);

    JobDataResponse toJobDataResponse(SalarySurveyEntity salarySurveyEntity);
    List<JobDataResponse> toListJobDataResponse(List<SalarySurveyEntity> salarySurveyEntities);
}
