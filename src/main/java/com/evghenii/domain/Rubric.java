package com.evghenii.domain;

import com.evghenii.serializator.RubricSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@JsonSerialize(using = RubricSerializer.class)
public class Rubric {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rubric_id")
    private int id;

    @NotNull(message = "Rubric name cannot be null")
    private String name;

    @Version
    private int version;

    public Rubric() {
    }

    public Rubric(int id) {
        this.id = id;
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
}
