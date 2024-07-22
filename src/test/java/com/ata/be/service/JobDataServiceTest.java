package com.ata.be.service;

import com.ata.be.dto.JobDataRequest;
import com.ata.be.entity.SalarySurveyEntity;
import com.ata.be.repository.SearchJobDataRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JobDataServiceTest {

    @InjectMocks
    private JobDataService jobDataService;
    @Mock
    private SearchJobDataRepository searchJobDataRepository;

    @Test
    void testSearchJobData() {
        JobDataRequest jobDataRequest = new JobDataRequest();
        ArrayList<SalarySurveyEntity> arrayList = new ArrayList<>();
        SalarySurveyEntity salarySurveyEntity = new SalarySurveyEntity();
        salarySurveyEntity.setId(1L);
        arrayList.add(salarySurveyEntity);
        PageImpl<SalarySurveyEntity> salarySurveyEntities = new PageImpl<>(arrayList, Pageable.unpaged(), 0);

        when(searchJobDataRepository.searchJobData(jobDataRequest, Pageable.ofSize(20))).thenReturn(salarySurveyEntities);

        jobDataService.searchJobData(jobDataRequest, Pageable.ofSize(20));

        verify(searchJobDataRepository, times(1)).searchJobData(jobDataRequest, Pageable.ofSize(20));
    }
}