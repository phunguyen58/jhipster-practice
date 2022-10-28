package com.leap.training.employee.service;

import com.leap.training.employee.domain.Region;
import com.leap.training.employee.repository.RegionRepository;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Region}.
 */
@Service
@Transactional
public class RegionService {

    private final Logger log = LoggerFactory.getLogger(RegionService.class);

    private final RegionRepository regionRepository;

    public RegionService(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    /**
     * Save a region.
     *
     * @param region the entity to save.
     * @return the persisted entity.
     */
    public Region save(Region region) {
        log.debug("Request to save Region : {}", region);
        return regionRepository.save(region);
    }

    /**
     * Partially update a region.
     *
     * @param region the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<Region> partialUpdate(Region region) {
        log.debug("Request to partially update Region : {}", region);

        return regionRepository
            .findById(region.getRegionId())
            .map(existingRegion -> {
                if (region.getRegionName() != null) {
                    existingRegion.setRegionName(region.getRegionName());
                }

                return existingRegion;
            })
            .map(regionRepository::save);
    }

    /**
     * Get all the regions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<Region> findAll(Pageable pageable) {
        log.debug("Request to get all Regions");
        return regionRepository.findAll(pageable);
    }

    /**
     * Get one region by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<Region> findOne(Long id) {
        log.debug("Request to get Region : {}", id);
        return regionRepository.findById(id);
    }

    /**
     * Delete the region by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Region : {}", id);
        regionRepository.deleteById(id);
    }
}
