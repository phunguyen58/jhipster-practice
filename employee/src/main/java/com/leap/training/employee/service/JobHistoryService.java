package com.leap.training.employee.service;

import com.leap.training.employee.domain.JobHistory;
import com.leap.training.employee.repository.JobHistoryRepository;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link JobHistory}.
 */
@Service
@Transactional
public class JobHistoryService {

    private final Logger log = LoggerFactory.getLogger(JobHistoryService.class);

    private final JobHistoryRepository jobHistoryRepository;

    public JobHistoryService(JobHistoryRepository jobHistoryRepository) {
        this.jobHistoryRepository = jobHistoryRepository;
    }

    /**
     * Save a jobHistory.
     *
     * @param jobHistory the entity to save.
     * @return the persisted entity.
     */
    public JobHistory save(JobHistory jobHistory) {
        log.debug("Request to save JobHistory : {}", jobHistory);
        return jobHistoryRepository.save(jobHistory);
    }

    /**
     * Partially update a jobHistory.
     *
     * @param jobHistory the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<JobHistory> partialUpdate(JobHistory jobHistory) {
        log.debug("Request to partially update JobHistory : {}", jobHistory);

        return jobHistoryRepository
            .findById(jobHistory.getId())
            .map(existingJobHistory -> {
                if (jobHistory.getEmployeeId() != null) {
                    existingJobHistory.setEmployeeId(jobHistory.getEmployeeId());
                }
                if (jobHistory.getStartDate() != null) {
                    existingJobHistory.setStartDate(jobHistory.getStartDate());
                }
                if (jobHistory.getEndDate() != null) {
                    existingJobHistory.setEndDate(jobHistory.getEndDate());
                }

                return existingJobHistory;
            })
            .map(jobHistoryRepository::save);
    }

    /**
     * Get all the jobHistories.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<JobHistory> findAll(Pageable pageable) {
        log.debug("Request to get all JobHistories");
        return jobHistoryRepository.findAll(pageable);
    }

    /**
     * Get one jobHistory by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<JobHistory> findOne(Long id) {
        log.debug("Request to get JobHistory : {}", id);
        return jobHistoryRepository.findById(id);
    }

    /**
     * Delete the jobHistory by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete JobHistory : {}", id);
        jobHistoryRepository.deleteById(id);
    }
}
