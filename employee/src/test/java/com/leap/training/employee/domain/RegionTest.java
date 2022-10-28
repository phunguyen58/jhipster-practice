package com.leap.training.employee.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.leap.training.employee.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RegionTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Region.class);
        Region region1 = new Region();
        region1.setRegionId(1L);
        Region region2 = new Region();
        region2.setRegionId(region1.getRegionId());
        assertThat(region1).isEqualTo(region2);
        region2.setRegionId(2L);
        assertThat(region1).isNotEqualTo(region2);
        region1.setRegionId(null);
        assertThat(region1).isNotEqualTo(region2);
    }
}
