package com.evghenii.dto;

import com.evghenii.domain.Person;

public class PersonDTO {

    private int id;
    private String name;
    private int version;
    private String adress;
    private String phones = "";
    private String emails = "";

    public PersonDTO() {
    }

    public PersonDTO(Person person) {
        this.id = person.getId();
        this.name = person.getName();
        this.adress = person.getAddress().getCity();
        this.version = person.getVersion();
        StringBuilder builderEmail = new StringBuilder();
        person.getEmails().forEach(e -> builderEmail.append(e.getEmail() + ", "));
        emails = builderEmail.toString();
        StringBuilder builderPhone = new StringBuilder();
        person.getPhones().forEach(e -> builderPhone.append(e.getPhoneNumber() + ", "));
        phones = builderPhone.toString();
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

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPhones() {
        return phones;
    }

    public void setPhones(String phones) {
        this.phones = phones;
    }

    public String getEmails() {
        return emails;
    }

    public void setEmails(String emails) {
        this.emails = emails;
    }

    @Override
    public String toString() {
        return "PersonDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", version=" + version +
                ", adress='" + adress + '\'' +
                ", phones=' " + phones + '\'' +
                ", emails='" + emails + '\'' +
                '}';
    }
}
