package com.example.lab2.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.lab2.R;
import com.example.lab2.databinding.ActivityMainBinding;
import com.example.lab2.databinding.Frag1LayoutBinding;
import com.example.lab2.model.Artist;

public class Frag1  extends Fragment {

    private Frag1LayoutBinding binding;

    @Override
    public View onCreateView (LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {
        binding = Frag1LayoutBinding.inflate(inflater, container, false);

        binding.btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String artistName = binding.artistInput.getText().toString();
                if(!artistName.isEmpty()){
                    Artist artist = new Artist(artistName);
                    artist.save();
                }
                else {
                    Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
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
}
