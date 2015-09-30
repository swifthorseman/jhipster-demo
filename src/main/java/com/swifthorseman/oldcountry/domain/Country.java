package com.swifthorseman.oldcountry.domain;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Country.
 */
@Entity
@Table(name = "COUNTRY")
public class Country implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull        
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Size(min = 3, max = 3)
    @Pattern(regexp = "[A-Z][A-Z][A-Z]")        
    @Column(name = "currency", length = 3, nullable = false)
    private String currency;

    @OneToOne
    private Currency country_currency;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Currency getCountry_currency() {
        return country_currency;
    }

    public void setCountry_currency(Currency currency) {
        this.country_currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Country country = (Country) o;

        if ( ! Objects.equals(id, country.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + "'" +
                ", currency='" + currency + "'" +
                '}';
    }
}
