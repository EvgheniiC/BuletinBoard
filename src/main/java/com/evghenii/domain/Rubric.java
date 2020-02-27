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

    @OneToMany(mappedBy = "rubric", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.REFRESH})
    private Set<Ad> ads = new HashSet<>();

   // private Collection<Ad> collectionAd = new ArrayList<>();

    public Rubric(Set<Ad> ads) {
        this.ads = ads;
    }

    public Rubric() {
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

    public void addAd(Ad ad) {
        ads.add(ad);
    }
}
