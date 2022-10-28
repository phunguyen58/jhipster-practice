package com.leap.training.employee.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Department.
 */
@Entity
@Table(name = "departments")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Department implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "department_id")
    private Long departmentId;

    @Column(name = "department_name")
    private String departmentName;

    @OneToMany(mappedBy = "department",  cascade = {CascadeType.REMOVE})
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "subEmployees", "managedDepartments", "job", "manager", "department" }, allowSetters = true)
    private Set<Employee> employees = new HashSet<>();

    @OneToMany(mappedBy = "department",  cascade = {CascadeType.REMOVE})
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "job", "department" }, allowSetters = true)
    private Set<JobHistory> jobHistories = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "manager_id")
    @JsonIgnoreProperties(value = { "subEmployees", "managedDepartments", "job", "manager", "department" }, allowSetters = true)
    private Employee manager;

    @ManyToOne
    @JoinColumn(name = "location_id")
    @JsonIgnoreProperties(value = { "departments", "country" }, allowSetters = true)
    private Location location;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getDepartmentId() {
        return this.departmentId;
    }

    public Department departmentId(Long departmentId) {
        this.setDepartmentId(departmentId);
        return this;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return this.departmentName;
    }

    public Department departmentName(String departmentName) {
        this.setDepartmentName(departmentName);
        return this;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Set<Employee> getEmployees() {
        return this.employees;
    }

    public void setEmployees(Set<Employee> employees) {
        if (this.employees != null) {
            this.employees.forEach(i -> i.setDepartment(null));
        }
        if (employees != null) {
            employees.forEach(i -> i.setDepartment(this));
        }
        this.employees = employees;
    }

    public Department employees(Set<Employee> employees) {
        this.setEmployees(employees);
        return this;
    }

    public Department addEmployees(Employee employee) {
        this.employees.add(employee);
        employee.setDepartment(this);
        return this;
    }

    public Department removeEmployees(Employee employee) {
        this.employees.remove(employee);
        employee.setDepartment(null);
        return this;
    }

    public Set<JobHistory> getJobHistories() {
        return this.jobHistories;
    }

    public void setJobHistories(Set<JobHistory> jobHistories) {
        if (this.jobHistories != null) {
            this.jobHistories.forEach(i -> i.setDepartment(null));
        }
        if (jobHistories != null) {
            jobHistories.forEach(i -> i.setDepartment(this));
        }
        this.jobHistories = jobHistories;
    }

    public Department jobHistories(Set<JobHistory> jobHistories) {
        this.setJobHistories(jobHistories);
        return this;
    }

    public Department addJobHistories(JobHistory jobHistory) {
        this.jobHistories.add(jobHistory);
        jobHistory.setDepartment(this);
        return this;
    }

    public Department removeJobHistories(JobHistory jobHistory) {
        this.jobHistories.remove(jobHistory);
        jobHistory.setDepartment(null);
        return this;
    }

    public Employee getManager() {
        return this.manager;
    }

    public void setManager(Employee employee) {
        this.manager = employee;
    }

    public Department manager(Employee employee) {
        this.setManager(employee);
        return this;
    }

    public Location getLocation() {
        return this.location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Department location(Location location) {
        this.setLocation(location);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Department)) {
            return false;
        }
        return departmentId != null && departmentId.equals(((Department) o).departmentId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Department{" +
            "departmentId=" + getDepartmentId() +
            ", departmentName='" + getDepartmentName() + "'" +
            "}";
    }
}
