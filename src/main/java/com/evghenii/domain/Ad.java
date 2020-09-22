package com.evghenii.domain;

import com.evghenii.deserializer.AdDeserializer;
import com.evghenii.serializator.AdSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@JsonDeserialize(using = AdDeserializer.class)
@JsonSerialize(using = AdSerializer.class)
public class Ad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ad_id")
    private int id;

    @NotNull(message = "Title cannot be null")
    private String title;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;

    @NotNull(message = "Text cannot be null")
    private String text;

    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_fk_id")
    private Person person;

    @ManyToOne(optional = false)
    @JoinColumn(name = "rubric_fk_id")
    private Rubric rubric;

    @Version
    private int version;

    private boolean active;

    public Ad() {
    }

    public Ad(int id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ad ad = (Ad) o;
        return id == ad.id &&
                version == ad.version &&
                active == ad.active &&
                title.equals(ad.title) &&
                date.equals(ad.date) &&
                text.equals(ad.text) &&
                price.equals(ad.price) &&
                person.equals(ad.person) &&
                rubric.equals(ad.rubric);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, date, text, price, person, rubric, version, active);
    }
}
