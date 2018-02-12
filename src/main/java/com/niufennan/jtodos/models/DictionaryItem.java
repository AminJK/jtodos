package com.niufennan.jtodos.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "dictionaryitems")
public class DictionaryItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer sort;
    private String dicname;
    private String dicvalue;
    private String typevalue;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getDicname() {
        return dicname;
    }

    public void setDicname(String dicname) {
        this.dicname = dicname;
    }

    public String getDicvalue() {
        return dicvalue;
    }

    public void setDicvalue(String dicvalue) {
        this.dicvalue = dicvalue;
    }

    public String getTypevalue() {
        return typevalue;
    }

    public void setTypevalue(String typevalue) {
        this.typevalue = typevalue;
    }
}
