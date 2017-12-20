package com.niufennan.jtodos.models;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity(name = "users")
public class User {
    @Id
    private Integer Id;
    private String name;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
