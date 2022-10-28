package com.leap.training.employee.web.rest;

import com.leap.training.employee.domain.Department;
import com.leap.training.employee.repository.DepartmentRepository;
import com.leap.training.employee.service.DepartmentService;
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
 * REST controller for managing {@link com.leap.training.employee.domain.Department}.
 */
@RestController
@RequestMapping("/api")
public class DepartmentResource {

    private final Logger log = LoggerFactory.getLogger(DepartmentResource.class);

    private static final String ENTITY_NAME = "employeeDepartment";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DepartmentService departmentService;

    private final DepartmentRepository departmentRepository;

    public DepartmentResource(DepartmentService departmentService, DepartmentRepository departmentRepository) {
        this.departmentService = departmentService;
        this.departmentRepository = departmentRepository;
    }

    /**
     * {@code POST  /departments} : Create a new department.
     *
     * @param department the department to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new department, or with status {@code 400 (Bad Request)} if the department has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/departments")
    public ResponseEntity<Department> createDepartment(@RequestBody Department department) throws URISyntaxException {
        log.debug("REST request to save Department : {}", department);
        if (department.getDepartmentId() != null) {
            throw new BadRequestAlertException("A new department cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Department result = departmentService.save(department);
        return ResponseEntity
            .created(new URI("/api/departments/" + result.getDepartmentId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getDepartmentId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /departments/:departmentId} : Updates an existing department.
     *
     * @param departmentId the id of the department to save.
     * @param department the department to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated department,
     * or with status {@code 400 (Bad Request)} if the department is not valid,
     * or with status {@code 500 (Internal Server Error)} if the department couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/departments/{departmentId}")
    public ResponseEntity<Department> updateDepartment(
        @PathVariable(value = "departmentId", required = false) final Long departmentId,
        @RequestBody Department department
    ) throws URISyntaxException {
        log.debug("REST request to update Department : {}, {}", departmentId, department);
        if (department.getDepartmentId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(departmentId, department.getDepartmentId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!departmentRepository.existsById(departmentId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Department result = departmentService.save(department);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, department.getDepartmentId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /departments/:departmentId} : Partial updates given fields of an existing department, field will ignore if it is null
     *
     * @param departmentId the id of the department to save.
     * @param department the department to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated department,
     * or with status {@code 400 (Bad Request)} if the department is not valid,
     * or with status {@code 404 (Not Found)} if the department is not found,
     * or with status {@code 500 (Internal Server Error)} if the department couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/departments/{departmentId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Department> partialUpdateDepartment(
        @PathVariable(value = "departmentId", required = false) final Long departmentId,
        @RequestBody Department department
    ) throws URISyntaxException {
        log.debug("REST request to partial update Department partially : {}, {}", departmentId, department);
        if (department.getDepartmentId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(departmentId, department.getDepartmentId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!departmentRepository.existsById(departmentId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Department> result = departmentService.partialUpdate(department);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, department.getDepartmentId().toString())
        );
    }

    /**
     * {@code GET  /departments} : get all the departments.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of departments in body.
     */
    @GetMapping("/departments")
    public ResponseEntity<List<Department>> getAllDepartments(Pageable pageable) {
        log.debug("REST request to get a page of Departments");
        Page<Department> page = departmentService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /departments/:id} : get the "id" department.
     *
     * @param id the id of the department to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the department, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/departments/{id}")
    public ResponseEntity<Department> getDepartment(@PathVariable Long id) {
        log.debug("REST request to get Department : {}", id);
        Optional<Department> department = departmentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(department);
    }

    /**
     * {@code DELETE  /departments/:id} : delete the "id" department.
     *
     * @param id the id of the department to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/departments/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
        log.debug("REST request to delete Department : {}", id);
        departmentService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
