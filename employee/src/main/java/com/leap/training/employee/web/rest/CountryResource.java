package com.leap.training.employee.web.rest;

import com.leap.training.employee.domain.Country;
import com.leap.training.employee.repository.CountryRepository;
import com.leap.training.employee.service.CountryService;
import com.leap.training.employee.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.leap.training.employee.domain.Country}.
 */
@RestController
@RequestMapping("/api")
public class CountryResource {

    private final Logger log = LoggerFactory.getLogger(CountryResource.class);

    private static final String ENTITY_NAME = "employeeCountry";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CountryService countryService;

    private final CountryRepository countryRepository;

    public CountryResource(CountryService countryService, CountryRepository countryRepository) {
        this.countryService = countryService;
        this.countryRepository = countryRepository;
    }

    /**
     * {@code POST  /countries} : Create a new country.
     *
     * @param country the country to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new country, or with status {@code 400 (Bad Request)} if the country has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/countries")
    public ResponseEntity<Country> createCountry(@RequestBody Country country) throws URISyntaxException {
        log.debug("REST request to save Country : {}", country);
        if (country.getCountryId() != null) {
            throw new BadRequestAlertException("A new country cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Country result = countryService.save(country);
        return ResponseEntity
            .created(new URI("/api/countries/" + result.getCountryId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getCountryId()))
            .body(result);
    }

    /**
     * {@code PUT  /countries/:countryId} : Updates an existing country.
     *
     * @param countryId the id of the country to save.
     * @param country the country to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated country,
     * or with status {@code 400 (Bad Request)} if the country is not valid,
     * or with status {@code 500 (Internal Server Error)} if the country couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/countries/{countryId}")
    public ResponseEntity<Country> updateCountry(
        @PathVariable(value = "countryId", required = false) final String countryId,
        @RequestBody Country country
    ) throws URISyntaxException {
        log.debug("REST request to update Country : {}, {}", countryId, country);
        if (country.getCountryId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(countryId, country.getCountryId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!countryRepository.existsById(countryId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Country result = countryService.save(country);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, country.getCountryId()))
            .body(result);
    }

    /**
     * {@code PATCH  /countries/:countryId} : Partial updates given fields of an existing country, field will ignore if it is null
     *
     * @param countryId the id of the country to save.
     * @param country the country to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated country,
     * or with status {@code 400 (Bad Request)} if the country is not valid,
     * or with status {@code 404 (Not Found)} if the country is not found,
     * or with status {@code 500 (Internal Server Error)} if the country couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/countries/{countryId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Country> partialUpdateCountry(
        @PathVariable(value = "countryId", required = false) final String countryId,
        @RequestBody Country country
    ) throws URISyntaxException {
        log.debug("REST request to partial update Country partially : {}, {}", countryId, country);
        if (country.getCountryId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(countryId, country.getCountryId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!countryRepository.existsById(countryId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Country> result = countryService.partialUpdate(country);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, country.getCountryId())
        );
    }

    /**
     * {@code GET  /countries} : get all the countries.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of countries in body.
     */
    @GetMapping("/countries")
    public ResponseEntity<List<Country>> getAllCountries(Pageable pageable) {
        log.debug("REST request to get a page of Countries");
        Page<Country> page = countryService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /countries/:id} : get the "id" country.
     *
     * @param id the id of the country to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the country, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/countries/{id}")
    public ResponseEntity<Country> getCountry(@PathVariable String id) {
        log.debug("REST request to get Country : {}", id);
        Optional<Country> country = countryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(country);
    }

    /**
     * {@code DELETE  /countries/:id} : delete the "id" country.
     *
     * @param id the id of the country to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/countries/{id}")
    public ResponseEntity<Void> deleteCountry(@PathVariable String id) {
        log.debug("REST request to delete Country : {}", id);
        countryService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id)).build();
    }
}
