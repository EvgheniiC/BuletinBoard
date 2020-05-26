package com.evghenii.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Objects;

@Entity

public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "email_id")
    private int id;

    @NotEmpty
    @NotNull(message = "Email cannot be null")
    private String email;

    @ManyToOne(optional = false)
    @JoinColumn(name = "person_fk_id")
    private Person person;

    @Version
    private int version;

    public Email() {
    }

    public Email(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email1 = (Email) o;
        return id == email1.id &&
                email.equals(email1.email) &&
                person.equals(email1.person);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, person);
    }
}
