package com.evghenii.domain;

import java.util.ArrayList;
import java.util.Collection;

public class Rubric {

    private Collection<Ad> collectionAd = new ArrayList<>();

    public Rubric(Collection<Ad> collectionAd) {
        this.collectionAd = collectionAd;
    }

    public Rubric() {
    }

    public Collection<Ad> getCollectionAd() {
        return collectionAd;
    }

    public void setCollectionAd(Collection<Ad> collectionAd) {
        this.collectionAd = collectionAd;
    }
}
