package com.example.lab2.model;

import androidx.annotation.NonNull;

import com.orm.SugarRecord;

public class Track extends SugarRecord {

    String name;
    Album album;
    Artist artist;

    public Track() {
    }

    public Track(String name, Album album, Artist artist) {
        this.name = name;
        this.album = album;
        this.artist = artist;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    @NonNull
    @Override
    public String toString() {
        return "name: " + name+
                "\nalbum title: " + album.getTitle() +
                "\nartist name: " + artist.getName();
    }
}
