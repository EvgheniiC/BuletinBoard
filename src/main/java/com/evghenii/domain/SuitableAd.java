package com.evghenii.domain;

import com.evghenii.serializator.SuitableAdSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
//@JsonSerialize(using = SuitableAdSerializer.class)
public class SuitableAd {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "suitable_id")
    private int id;

    @NotEmpty
    @NotNull(message = "Name cannot be null")
    private String title;

    private BigDecimal priceFrom;

    private BigDecimal priceTo;

    @ManyToOne
    @JoinColumn(name = "person_fk_id")
    private Person person;

    @ManyToOne
    @JoinColumn(name = "rubric_fk_id")
    private Rubric rubric;

    @Version
    private int version;

    public SuitableAd() {
    }

    public SuitableAd(int id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPriceFrom() {
        return priceFrom;
    }

    public void setPriceFrom(BigDecimal priceFrom) {
        this.priceFrom = priceFrom;
    }

    public BigDecimal getPriceTo() {
        return priceTo;
    }

    public void setPriceTo(BigDecimal priceTo) {
        this.priceTo = priceTo;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Rubric getRubric() {
        return rubric;
    }

    public void setRubric(Rubric rubric) {
        this.rubric = rubric;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SuitableAd that = (SuitableAd) o;
        return id == that.id &&
                title.equals(that.title) &&
                priceFrom.equals(that.priceFrom) &&
                priceTo.equals(that.priceTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, priceFrom, priceTo);
    }
}
