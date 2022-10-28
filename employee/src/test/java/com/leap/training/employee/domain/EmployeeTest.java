package com.leap.training.employee.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.leap.training.employee.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class EmployeeTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Employee.class);
        Employee employee1 = new Employee();
        employee1.setEmployeeId(1L);
        Employee employee2 = new Employee();
        employee2.setEmployeeId(employee1.getEmployeeId());
        assertThat(employee1).isEqualTo(employee2);
        employee2.setEmployeeId(2L);
        assertThat(employee1).isNotEqualTo(employee2);
        employee1.setEmployeeId(null);
        assertThat(employee1).isNotEqualTo(employee2);
    }
}
