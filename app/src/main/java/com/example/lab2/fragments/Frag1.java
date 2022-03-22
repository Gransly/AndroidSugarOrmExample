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

import com.example.lab2.databinding.Frag1LayoutBinding;
import com.example.lab2.model.Album;
import com.example.lab2.model.Artist;

import java.util.List;

public class Frag1 extends Fragment {

    private Frag1LayoutBinding binding;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        binding = Frag1LayoutBinding.inflate(inflater, container, false);

        updateSpinnerArtist();

        binding.btnCreateAuthor.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                String artistName = binding.artistInput.getText().toString();
                if (!artistName.isEmpty()) {
                    Artist artist = new Artist(artistName);
                    artist.save();
                    binding.artistInput.setText("");
                    updateSpinnerArtist();
                } else {
                    Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.btnCreateAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String albumName = binding.albumNameInput.getText().toString();

                String authorName = binding.spinnerAuthor.getSelectedItem().toString();
                if (!albumName.isEmpty() && !authorName.isEmpty()) {
                   List<Artist> artists = Artist.find(Artist.class, "name = ?", authorName); // Костыль
                    if (artists != null) {
                            Album album = new Album(albumName, artists.get(0));
                            album.save();
                            binding.albumNameInput.setText("");
                    } else {
                        Toast.makeText(getActivity(), "Not found an artist", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return binding.getRoot();
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
