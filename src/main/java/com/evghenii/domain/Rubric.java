package com.evghenii.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

import static com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;

@Entity
@JsonIdentityInfo(generator = PropertyGenerator.class, property = "id")
public class Rubric {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rubric_id")
    private int id;

    @NotNull(message = "Rubric name cannot be null")
    private String name;

    @OneToMany(mappedBy = "rubric", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.REFRESH})
    private Set<Ad> ads = new HashSet<>();

    @Version
    private int version;

    public Rubric() {
    }

    public Rubric(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Ad> getAds() {
        return ads;
    }

    public void setAds(Set<Ad> ads) {
        this.ads = ads;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addAd(Ad ad) {
        ads.add(ad);
    }

    public void removedAd(Ad ad) {
        ads.remove(ad);
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
