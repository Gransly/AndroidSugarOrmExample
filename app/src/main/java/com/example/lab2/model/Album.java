package com.example.lab2.model;

import com.orm.SugarRecord;

public class Album extends SugarRecord {

    private String title;
    private Artist artist;

    public Album(String title, Artist artist) {
        this.title = title;
        this.artist = artist;
    }

    public Album() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    @Override
    public String toString() {
        return  title;
    }
}
