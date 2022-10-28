package com.leap.training.employee.web.rest;

import com.leap.training.employee.domain.Employee;
import com.leap.training.employee.repository.EmployeeRepository;
import com.leap.training.employee.service.EmployeeService;
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
 * REST controller for managing {@link com.leap.training.employee.domain.Employee}.
 */
@RestController
@RequestMapping("/api")
public class EmployeeResource {

    private final Logger log = LoggerFactory.getLogger(EmployeeResource.class);

    private static final String ENTITY_NAME = "employeeEmployee";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EmployeeService employeeService;

    private final EmployeeRepository employeeRepository;

    public EmployeeResource(EmployeeService employeeService, EmployeeRepository employeeRepository) {
        this.employeeService = employeeService;
        this.employeeRepository = employeeRepository;
    }

    /**
     * {@code POST  /employees} : Create a new employee.
     *
     * @param employee the employee to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new employee, or with status {@code 400 (Bad Request)} if the employee has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/employees")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) throws URISyntaxException {
        log.debug("REST request to save Employee : {}", employee);
        if (employee.getEmployeeId() != null) {
            throw new BadRequestAlertException("A new employee cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Employee result = employeeService.save(employee);
        return ResponseEntity
            .created(new URI("/api/employees/" + result.getEmployeeId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getEmployeeId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /employees/:employeeId} : Updates an existing employee.
     *
     * @param employeeId the id of the employee to save.
     * @param employee the employee to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated employee,
     * or with status {@code 400 (Bad Request)} if the employee is not valid,
     * or with status {@code 500 (Internal Server Error)} if the employee couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/employees/{employeeId}")
    public ResponseEntity<Employee> updateEmployee(
        @PathVariable(value = "employeeId", required = false) final Long employeeId,
        @RequestBody Employee employee
    ) throws URISyntaxException {
        log.debug("REST request to update Employee : {}, {}", employeeId, employee);
        if (employee.getEmployeeId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(employeeId, employee.getEmployeeId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!employeeRepository.existsById(employeeId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Employee result = employeeService.save(employee);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, employee.getEmployeeId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /employees/:employeeId} : Partial updates given fields of an existing employee, field will ignore if it is null
     *
     * @param employeeId the id of the employee to save.
     * @param employee the employee to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated employee,
     * or with status {@code 400 (Bad Request)} if the employee is not valid,
     * or with status {@code 404 (Not Found)} if the employee is not found,
     * or with status {@code 500 (Internal Server Error)} if the employee couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/employees/{employeeId}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Employee> partialUpdateEmployee(
        @PathVariable(value = "employeeId", required = false) final Long employeeId,
        @RequestBody Employee employee
    ) throws URISyntaxException {
        log.debug("REST request to partial update Employee partially : {}, {}", employeeId, employee);
        if (employee.getEmployeeId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(employeeId, employee.getEmployeeId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!employeeRepository.existsById(employeeId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Employee> result = employeeService.partialUpdate(employee);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, employee.getEmployeeId().toString())
        );
    }

    /**
     * {@code GET  /employees} : get all the employees.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of employees in body.
     */
    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees(Pageable pageable) {
        log.debug("REST request to get a page of Employees");
        Page<Employee> page = employeeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /employees/:id} : get the "id" employee.
     *
     * @param id the id of the employee to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the employee, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Long id) {
        log.debug("REST request to get Employee : {}", id);
        Optional<Employee> employee = employeeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(employee);
    }

    /**
     * {@code DELETE  /employees/:id} : delete the "id" employee.
     *
     * @param id the id of the employee to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        log.debug("REST request to delete Employee : {}", id);
        employeeService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
