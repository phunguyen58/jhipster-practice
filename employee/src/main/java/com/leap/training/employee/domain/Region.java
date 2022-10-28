package com.leap.training.employee.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Region.
 */
@Entity
@Table(name = "regions")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Region implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "region_id")
    private Long regionId;

    @Column(name = "region_name")
    private String regionName;

    @OneToMany(mappedBy = "region", cascade = {CascadeType.REMOVE})
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "locations", "region" }, allowSetters = true)
    private Set<Country> countries = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getRegionId() {
        return this.regionId;
    }

    public Region regionId(Long regionId) {
        this.setRegionId(regionId);
        return this;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public String getRegionName() {
        return this.regionName;
    }

    public Region regionName(String regionName) {
        this.setRegionName(regionName);
        return this;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public Set<Country> getCountries() {
        return this.countries;
    }

    public void setCountries(Set<Country> countries) {
        if (this.countries != null) {
            this.countries.forEach(i -> i.setRegion(null));
        }
        if (countries != null) {
            countries.forEach(i -> i.setRegion(this));
        }
        this.countries = countries;
    }

    public Region countries(Set<Country> countries) {
        this.setCountries(countries);
        return this;
    }

    public Region addCountries(Country country) {
        this.countries.add(country);
        country.setRegion(this);
        return this;
    }

    public Region removeCountries(Country country) {
        this.countries.remove(country);
        country.setRegion(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Region)) {
            return false;
        }
        return regionId != null && regionId.equals(((Region) o).regionId);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Region{" +
            "regionId=" + getRegionId() +
            ", regionName='" + getRegionName() + "'" +
            "}";
    }
}
