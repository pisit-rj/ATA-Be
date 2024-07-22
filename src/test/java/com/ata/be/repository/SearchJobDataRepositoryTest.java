package com.ata.be.repository;

import com.ata.be.dto.JobDataRequest;
import com.ata.be.entity.SalarySurveyEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ImportAutoConfiguration(SearchJobDataRepository.class)
class SearchJobDataRepositoryTest {

    @Autowired
    private SearchJobDataRepository searchJobDataRepository;

    @Test
    void testSearchJobDataSuccessfully() {
        JobDataRequest jobDataRequest = new JobDataRequest();
        Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "jobTitle");

        PageImpl<SalarySurveyEntity> salarySurveyEntities = searchJobDataRepository.searchJobData(jobDataRequest, pageable);

        assertNotNull(salarySurveyEntities.getContent());
    }

    @Test
    void testSearchJobDataWithFilterColumnSuccessfully() {
        JobDataRequest jobDataRequest = new JobDataRequest();
        jobDataRequest.setField(List.of("jobTitle", "salary"));
        Pageable pageable = PageRequest.of(0, 100, Sort.Direction.DESC, "jobTitle");

        PageImpl<SalarySurveyEntity> salarySurveyEntities = searchJobDataRepository.searchJobData(jobDataRequest, pageable);

        int random = (int) (Math.random() * 10);
        assertNull(salarySurveyEntities.getContent().get(random).getId());
        assertNull(salarySurveyEntities.getContent().get(random).getGender());
        assertNull(salarySurveyEntities.getContent().get(random).getTimestamp());
        assertNull(salarySurveyEntities.getContent().get(random).getEmployer());
        assertNull(salarySurveyEntities.getContent().get(random).getAnnualBonus());
        assertNull(salarySurveyEntities.getContent().get(random).getLocation());
        assertNull(salarySurveyEntities.getContent().get(random).getAdditionalComments());
        assertNull(salarySurveyEntities.getContent().get(random).getSigningBonus());
        assertNull(salarySurveyEntities.getContent().get(random).getYearsAtEmployer());
        assertNull(salarySurveyEntities.getContent().get(random).getAdditionalComments());
    }

    @Test
    void testSearchJobDataWithFilterByJobTitleSuccessfully() {
        JobDataRequest jobDataRequest = new JobDataRequest();
        jobDataRequest.setJobTitle("Software Engineer");
        Pageable pageable = PageRequest.of(0, 100, Sort.Direction.DESC, "jobTitle");

        PageImpl<SalarySurveyEntity> salarySurveyEntities = searchJobDataRepository.searchJobData(jobDataRequest, pageable);

        int random = (int) (Math.random() * 10);
        assertNotNull(salarySurveyEntities.getContent());
        assertTrue(salarySurveyEntities.getContent().get(random).getJobTitle().contains("Software Engineer"));
    }

    @Test
    void testSearchJobDataWithOrderByIdSuccessfully() {
        JobDataRequest jobDataRequest = new JobDataRequest();
        Pageable pageable = PageRequest.of(0, 100, Sort.Direction.DESC, "id");

        PageImpl<SalarySurveyEntity> salarySurveyEntities = searchJobDataRepository.searchJobData(jobDataRequest, pageable);

        assertNotNull(salarySurveyEntities.getContent());
        assertTrue(salarySurveyEntities.getContent().get(0).getId() > salarySurveyEntities.getContent().get(1).getId());
    }
}