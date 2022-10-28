package com.leap.training.employee.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.leap.training.employee.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class JobTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Job.class);
        Job job1 = new Job();
        job1.setJobId("id1");
        Job job2 = new Job();
        job2.setJobId(job1.getJobId());
        assertThat(job1).isEqualTo(job2);
        job2.setJobId("id2");
        assertThat(job1).isNotEqualTo(job2);
        job1.setJobId(null);
        assertThat(job1).isNotEqualTo(job2);
    }
}
