package com.example.lab2.fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.lab2.databinding.Frag2LayoutBinding;
import com.example.lab2.model.Album;
import com.example.lab2.model.Artist;
import com.example.lab2.model.Track;
import com.example.lab2.service.TrackService;

import java.util.List;

public class TrackCreateFragment extends Fragment {
    private Frag2LayoutBinding binding;
    private final TrackService trackService = new TrackService(getActivity());

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
                trackService.createTrack(binding.trackNameInput.getText().toString(),
                        (Artist) binding.spinnerAuthor.getSelectedItem(),
                        (Album) binding.spinnerAlbum.getSelectedItem(),
                        new TrackService.CreateCallback() {
                            @Override
                            public void onError(String message) {
                                Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onResponse(String message) {
                                Toast.makeText(getActivity(), "Saved!\n" + message, Toast.LENGTH_LONG).show();
                            }
                        });
                binding.trackNameInput.setText("");
            }
        });

        binding.fbtnReloadData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getActivity(), "Update", Toast.LENGTH_LONG).show();
                updateSpinnerArtist();
                updateSpinnerAlbum();
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void updateSpinnerArtist() {
        List<Artist> artists = Artist.listAll(Artist.class);
        ArrayAdapter spinnerAdapter = new ArrayAdapter(getActivity(),
                android.R.layout.simple_list_item_1, artists);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        binding.spinnerAuthor.setAdapter(spinnerAdapter);
    }

    private void updateSpinnerAlbum() {
        List<Album> albums = Album.listAll(Album.class);
        ArrayAdapter spinnerAdapter = new ArrayAdapter(getActivity(),
                android.R.layout.simple_list_item_1, albums);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        binding.spinnerAlbum.setAdapter(spinnerAdapter);
    }
}
