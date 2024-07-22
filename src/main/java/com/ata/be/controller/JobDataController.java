package com.ata.be.controller;

import com.ata.be.dto.JobDataRequest;
import com.ata.be.dto.JobDataResponse;
import com.ata.be.service.JobDataService;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobDataController {

    private final JobDataService jobDataService;

    public JobDataController(JobDataService jobDataService) {
        this.jobDataService = jobDataService;
    }

    @GetMapping("/jobData")
    public PageImpl<JobDataResponse> getJobData(@ParameterObject final JobDataRequest jobDataRequest,
                                                @PageableDefault(size = Integer.MAX_VALUE, sort = "jobTitle", direction = Sort.Direction.DESC) Pageable pageable) {
        return jobDataService.searchJobData(jobDataRequest, pageable);
    }
}
