package com.example.lab2.model;

import com.orm.SugarRecord;

public class Artist extends SugarRecord {

    String name;

    public Artist() {
    }

    public Artist(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
