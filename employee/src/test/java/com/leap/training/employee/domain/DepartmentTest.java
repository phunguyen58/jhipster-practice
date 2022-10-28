package com.leap.training.employee.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.leap.training.employee.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class DepartmentTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Department.class);
        Department department1 = new Department();
        department1.setDepartmentId(1L);
        Department department2 = new Department();
        department2.setDepartmentId(department1.getDepartmentId());
        assertThat(department1).isEqualTo(department2);
        department2.setDepartmentId(2L);
        assertThat(department1).isNotEqualTo(department2);
        department1.setDepartmentId(null);
        assertThat(department1).isNotEqualTo(department2);
    }
}
