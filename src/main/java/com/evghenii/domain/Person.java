package com.evghenii.domain;

import com.evghenii.serializator.PersonSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@JsonSerialize(using = PersonSerializer.class)
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private int id;

    @NotEmpty
    @NotNull(message = "Name cannot be null")
    private String name;

    @NotEmpty
    @NotNull(message = "Password cannot be null")
    private String password;

    @OneToMany(mappedBy = "person", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE,
            CascadeType.MERGE})
    private Set<Phone> phones = new HashSet<>();

    @OneToMany(mappedBy = "person", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE,
            CascadeType.MERGE})
    private Set<Ad> ads = new HashSet<>();

    @OneToMany(mappedBy = "person", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE,
            CascadeType.MERGE})
    private Set<Email> emails = new HashSet<>();

    @OneToOne(optional = false, cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
    @JoinColumn(name = "address_fk_id")
    private Address address;

    @Version
    private int version;

    @OneToMany(mappedBy = "person", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE,
            CascadeType.MERGE})
    private Set<SuitableAd> suitableAds = new HashSet<>();

    public Person() {
    }

    public Person(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Phone> getPhones() {
        return phones;
    }

    public void setPhones(Set<Phone> phones) {
        this.phones = phones;
    }

    public Set<Email> getEmails() {
        return emails;
    }

    public void setEmails(Set<Email> emails) {
        this.emails = emails;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<Ad> getAds() {
        return ads;
    }

    public void setAds(Set<Ad> ads) {
        this.ads = ads;
    }

    public void removedAd(Ad ad) {
        ads.remove(ad);
    }

    public void addAd(Ad ad) {
        ads.add(ad);
    }

    public void removeAllAd(List<Ad> ad) {
        ads.removeAll(ad);
    }

    public void removePhone(Phone phone) {
        phones.remove(phone);
    }

    public void addPhone(Phone phone) {
        phones.add(phone);
    }

    public void addEmail(Email email) {
        emails.add(email);
    }

    public void removeEmail(Email email) {
        emails.remove(email);
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Set<SuitableAd> getSuitableAds() {
        return suitableAds;
    }

    public void setSuitableAds(Set<SuitableAd> suitableAds) {
        this.suitableAds = suitableAds;
    }

    public void addSuitableAdd(SuitableAd ad) {
        suitableAds.add(ad);
    }

    public void removeSuitableAdd(SuitableAd ad) {
        suitableAds.remove(ad);
    }

}
