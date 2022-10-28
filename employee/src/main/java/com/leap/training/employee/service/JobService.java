package com.leap.training.employee.service;

import com.leap.training.employee.domain.Job;
import com.leap.training.employee.repository.JobRepository;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Job}.
 */
@Service
@Transactional
public class JobService {

    private final Logger log = LoggerFactory.getLogger(JobService.class);

    private final JobRepository jobRepository;

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    /**
     * Save a job.
     *
     * @param job the entity to save.
     * @return the persisted entity.
     */
    public Job save(Job job) {
        log.debug("Request to save Job : {}", job);
        return jobRepository.save(job);
    }

    /**
     * Partially update a job.
     *
     * @param job the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<Job> partialUpdate(Job job) {
        log.debug("Request to partially update Job : {}", job);

        return jobRepository
            .findById(job.getJobId())
            .map(existingJob -> {
                if (job.getJobTitle() != null) {
                    existingJob.setJobTitle(job.getJobTitle());
                }
                if (job.getMinSalary() != null) {
                    existingJob.setMinSalary(job.getMinSalary());
                }
                if (job.getMaxSalary() != null) {
                    existingJob.setMaxSalary(job.getMaxSalary());
                }

                return existingJob;
            })
            .map(jobRepository::save);
    }

    /**
     * Get all the jobs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Job> findAll(Pageable pageable) {
        log.debug("Request to get all Jobs");
        return jobRepository.findAll(pageable);
    }

    /**
     * Get one job by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Job> findOne(String id) {
        log.debug("Request to get Job : {}", id);
        return jobRepository.findById(id);
    }

    /**
     * Delete the job by id.
     *
     * @param id the id of the entity.
     */
    public void delete(String id) {
        log.debug("Request to delete Job : {}", id);
        jobRepository.deleteById(id);
    }
}
