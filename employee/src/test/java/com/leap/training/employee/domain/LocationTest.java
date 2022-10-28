package com.leap.training.employee.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.leap.training.employee.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class LocationTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Location.class);
        Location location1 = new Location();
        location1.setLocationId(1L);
        Location location2 = new Location();
        location2.setLocationId(location1.getLocationId());
        assertThat(location1).isEqualTo(location2);
        location2.setLocationId(2L);
        assertThat(location1).isNotEqualTo(location2);
        location1.setLocationId(null);
        assertThat(location1).isNotEqualTo(location2);
    }
}
