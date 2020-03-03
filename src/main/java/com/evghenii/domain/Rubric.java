package com.evghenii.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Rubric {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "rubric_id")
    private int id;

    private String name;

    @OneToMany(mappedBy = "rubric", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.REFRESH})
    private Set<Ad> ads = new HashSet<>();

    public Rubric() {
    }

    public Rubric(Set<Ad> ads) {
        this.ads = ads;
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

}
