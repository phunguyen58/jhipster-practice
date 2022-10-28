package com.leap.training.employee.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.leap.training.employee.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CountryTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Country.class);
        Country country1 = new Country();
        country1.setCountryId("id1");
        Country country2 = new Country();
        country2.setCountryId(country1.getCountryId());
        assertThat(country1).isEqualTo(country2);
        country2.setCountryId("id2");
        assertThat(country1).isNotEqualTo(country2);
        country1.setCountryId(null);
        assertThat(country1).isNotEqualTo(country2);
    }
}
