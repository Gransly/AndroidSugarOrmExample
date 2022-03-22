package com.example.lab2.service;

import android.content.Context;
import android.widget.Toast;

import com.example.lab2.model.Album;
import com.example.lab2.model.Artist;
import com.example.lab2.model.Track;

public class TrackService {

    Context context;

    public TrackService(Context context) {
        this.context = context;
    }

    public interface CreateCallback {
        void onError(String message);

        void onResponse(String message);
    }

    public void createArtist(String artistName, CreateCallback createCallback){
        if (!artistName.isEmpty()) {
            Artist artist = new Artist(artistName);
            artist.save();
            createCallback.onResponse(artist.toString());
        } else {
            createCallback.onError("Error, empty input field");
        }
    }

    public void createAlbum(String albumName, Artist artist, CreateCallback createCallback){
        if (!albumName.isEmpty() && artist != null) {
            Album album = new Album(albumName, artist);
            album.save();
            createCallback.onResponse(album.toString());
        } else {
            createCallback.onError("Error, empty input field");
        }
    }

    public void createTrack(String trackName, Artist artist, Album album, CreateCallback createCallback){
        if (artist != null && album != null && !trackName.isEmpty()) {
            Track track = new Track(trackName, album, artist);
            track.save();
            createCallback.onResponse(track.toString());
        } else {
            createCallback.onError("Error, empty input field");
        }
    }
}
