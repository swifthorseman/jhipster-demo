package com.swifthorseman.oldcountry.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.swifthorseman.oldcountry.domain.util.CustomLocalDateSerializer;
import com.swifthorseman.oldcountry.domain.util.ISO8601LocalDateDeserializer;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A CardProductType.
 */
@Entity
@Table(name = "CARD_PRODUCT_TYPE")
public class CardProductType implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(max = 100)        
    @Column(name = "card_band", length = 100)
    private String card_band;

    @Size(max = 100)        
    @Column(name = "card_product_type_code", length = 100)
    private String card_product_type_code;

    @Size(max = 100)        
    @Column(name = "card_product_type_name", length = 100)
    private String card_product_type_name;

    @Size(max = 100)        
    @Column(name = "card_scheme_id", length = 100)
    private String card_scheme_id;

    @Size(max = 100)        
    @Column(name = "card_scheme_name", length = 100)
    private String card_scheme_name;
    
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    @JsonSerialize(using = CustomLocalDateSerializer.class)
    @JsonDeserialize(using = ISO8601LocalDateDeserializer.class)
    @Column(name = "loading_date")
    private LocalDate loading_date;

    @Size(max = 100)        
    @Column(name = "scheme", length = 100)
    private String scheme;

    @Size(max = 100)        
    @Column(name = "debit_credit", length = 100)
    private String debit_credit;

    @Size(min = 1, max = 1)        
    @Column(name = "display_flag", length = 1)
    private String display_flag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCard_band() {
        return card_band;
    }

    public void setCard_band(String card_band) {
        this.card_band = card_band;
    }

    public String getCard_product_type_code() {
        return card_product_type_code;
    }

    public void setCard_product_type_code(String card_product_type_code) {
        this.card_product_type_code = card_product_type_code;
    }

    public String getCard_product_type_name() {
        return card_product_type_name;
    }

    public void setCard_product_type_name(String card_product_type_name) {
        this.card_product_type_name = card_product_type_name;
    }

    public String getCard_scheme_id() {
        return card_scheme_id;
    }

    public void setCard_scheme_id(String card_scheme_id) {
        this.card_scheme_id = card_scheme_id;
    }

    public String getCard_scheme_name() {
        return card_scheme_name;
    }

    public void setCard_scheme_name(String card_scheme_name) {
        this.card_scheme_name = card_scheme_name;
    }

    public LocalDate getLoading_date() {
        return loading_date;
    }

    public void setLoading_date(LocalDate loading_date) {
        this.loading_date = loading_date;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getDebit_credit() {
        return debit_credit;
    }

    public void setDebit_credit(String debit_credit) {
        this.debit_credit = debit_credit;
    }

    public String getDisplay_flag() {
        return display_flag;
    }

    public void setDisplay_flag(String display_flag) {
        this.display_flag = display_flag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CardProductType cardProductType = (CardProductType) o;

        if ( ! Objects.equals(id, cardProductType.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "CardProductType{" +
                "id=" + id +
                ", card_band='" + card_band + "'" +
                ", card_product_type_code='" + card_product_type_code + "'" +
                ", card_product_type_name='" + card_product_type_name + "'" +
                ", card_scheme_id='" + card_scheme_id + "'" +
                ", card_scheme_name='" + card_scheme_name + "'" +
                ", loading_date='" + loading_date + "'" +
                ", scheme='" + scheme + "'" +
                ", debit_credit='" + debit_credit + "'" +
                ", display_flag='" + display_flag + "'" +
                '}';
    }
}
