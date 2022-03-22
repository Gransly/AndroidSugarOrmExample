package com.example.lab2.fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.lab2.databinding.Frag2LayoutBinding;
import com.example.lab2.model.Album;
import com.example.lab2.model.Artist;
import com.example.lab2.model.Track;

import java.util.List;

public class Frag2 extends Fragment {
    private Frag2LayoutBinding binding;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        binding = Frag2LayoutBinding.inflate(inflater, container, false);

        updateSpinnerArtist();
        updateSpinnerAlbum();

        binding.btnCreateTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String trackName = binding.trackNameInput.getText().toString();
                String authorName = binding.spinnerAuthor.getSelectedItem().toString();
                String albumTitle = binding.spinnerAlbum.getSelectedItem().toString();

                if (!albumTitle.isEmpty() && !authorName.isEmpty() && !trackName.isEmpty()) {
                    List<Artist> artists = Artist.find(Artist.class, "name = ?", authorName);
                    List<Album> albums = Album.find(Album.class, "title = ?", albumTitle);
                    if (artists != null && albums != null) {
                        Track track = new Track(trackName, albums.get(0), artists.get(0));
                        track.save();
                        binding.trackNameInput.setText("");
                    } else {
                        Toast.makeText(getActivity(), "Not found an artist or album", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(), "Error of saving", Toast.LENGTH_SHORT).show();
                }
            }
        });



        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void updateSpinnerArtist() {
        List<Artist> artists = Artist.listAll(Artist.class);
        String[] artistNames = artists.stream().map(Artist::getName).toArray(String[]::new);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, artistNames);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        binding.spinnerAuthor.setAdapter(spinnerAdapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void updateSpinnerAlbum() {
        List<Album> albums = Album.listAll(Album.class);
        String[] albumsTitles = albums.stream().map(Album::getTitle).toArray(String[]::new);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, albumsTitles);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        binding.spinnerAlbum.setAdapter(spinnerAdapter);
    }
}
