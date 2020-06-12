package com.evghenii.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "phone_id")
    private int id;

    @Column(name = "phone_number")
    private String phoneNumber;

    @ManyToOne(optional = false)
    @JoinColumn(name = "person_fk_id")
    private Person person;

    @Version
    private int version;

    public Phone() {
    }

    public Phone(int id) {
        this.id = id;
    }

    public Phone(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
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
        Phone phone = (Phone) o;
        return id == phone.id &&
                phoneNumber.equals(phone.phoneNumber) &&
                person.equals(phone.person);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, phoneNumber, person);
    }
}
