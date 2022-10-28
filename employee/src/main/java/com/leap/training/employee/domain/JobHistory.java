package com.leap.training.employee.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A JobHistory.
 */
@Entity
@Table(name = "job_history")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class JobHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "start_date")
    private Long startDate;

    @Column(name = "end_date")
    private Long endDate;

    @ManyToOne
    @JsonIgnoreProperties(value = { "employees", "jobHistories" }, allowSetters = true)
    private Job job;

    @ManyToOne
    @JsonIgnoreProperties(value = { "employees", "jobHistories", "manager", "location" }, allowSetters = true)
    private Department department;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public JobHistory id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEmployeeId() {
        return this.employeeId;
    }

    public JobHistory employeeId(Long employeeId) {
        this.setEmployeeId(employeeId);
        return this;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getStartDate() {
        return this.startDate;
    }

    public JobHistory startDate(Long startDate) {
        this.setStartDate(startDate);
        return this;
    }

    public void setStartDate(Long startDate) {
        this.startDate = startDate;
    }

    public Long getEndDate() {
        return this.endDate;
    }

    public JobHistory endDate(Long endDate) {
        this.setEndDate(endDate);
        return this;
    }

    public void setEndDate(Long endDate) {
        this.endDate = endDate;
    }

    public Job getJob() {
        return this.job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public JobHistory job(Job job) {
        this.setJob(job);
        return this;
    }

    public Department getDepartment() {
        return this.department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public JobHistory department(Department department) {
        this.setDepartment(department);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof JobHistory)) {
            return false;
        }
        return id != null && id.equals(((JobHistory) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "JobHistory{" +
            "id=" + getId() +
            ", employeeId=" + getEmployeeId() +
            ", startDate=" + getStartDate() +
            ", endDate=" + getEndDate() +
            "}";
    }
}
