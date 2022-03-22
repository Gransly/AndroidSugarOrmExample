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
import com.example.lab2.service.TrackService;

import java.util.List;

public class ArtistCreateFragment extends Fragment {

    private Frag1LayoutBinding binding;
    private final TrackService trackService = new TrackService(getActivity());

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
                trackService.createArtist(binding.artistInput.getText().toString(), new TrackService.CreateCallback() {
                    @Override
                    public void onError(String message) {
                        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String message) {
                        Toast.makeText(getActivity(), "Saved! " + message, Toast.LENGTH_LONG).show();
                        updateSpinnerArtist();
                    }
                });
                binding.artistInput.setText("");
            }
        });

        binding.btnCreateAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                trackService.createAlbum(binding.albumNameInput.getText().toString(),
                        (Artist) binding.spinnerAuthor.getSelectedItem(),
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
                binding.albumNameInput.setText("");
            }
        });

        return binding.getRoot();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void updateSpinnerArtist() {
        List<Artist> artists = Artist.listAll(Artist.class);
        ArrayAdapter spinnerAdapter = new ArrayAdapter(getActivity(),
                android.R.layout.simple_list_item_1, artists);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        binding.spinnerAuthor.setAdapter(spinnerAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
