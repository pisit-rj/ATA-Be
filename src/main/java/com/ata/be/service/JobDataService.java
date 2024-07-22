package com.ata.be.service;

import com.ata.be.dto.JobDataRequest;
import com.ata.be.dto.JobDataResponse;
import com.ata.be.entity.SalarySurveyEntity;
import com.ata.be.mapper.SalarySurveyMapper;
import com.ata.be.repository.SearchJobDataRepository;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobDataService {

    private final SearchJobDataRepository searchJobDataRepository;

    public JobDataService(SearchJobDataRepository searchJobDataRepository) {
        this.searchJobDataRepository = searchJobDataRepository;
    }

    public PageImpl<JobDataResponse> searchJobData(JobDataRequest jobDataRequest, Pageable pageable) {
        PageImpl<SalarySurveyEntity> salarySurveyEntities = searchJobDataRepository.searchJobData(jobDataRequest, pageable);
        List<JobDataResponse> jobDataResponses = SalarySurveyMapper.INSTANCE.toListJobDataResponse(salarySurveyEntities.getContent());
        return new PageImpl<>(jobDataResponses, pageable, salarySurveyEntities.getTotalElements());
    }
}
