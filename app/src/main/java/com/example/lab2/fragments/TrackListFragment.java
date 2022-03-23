package com.example.lab2.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.lab2.R;
import com.example.lab2.databinding.Frag3LayoutBinding;
import com.example.lab2.model.Track;

import java.util.List;

public class TrackListFragment extends Fragment {
    private Frag3LayoutBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        binding = Frag3LayoutBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        updateList();

        binding.fbtnReloadData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getActivity(), "Update", Toast.LENGTH_LONG).show();
                updateList();
            }

        });

        binding.lvTracks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Track track = (Track)parent.getAdapter().getItem(position);
                Toast.makeText(getActivity(), track.toString(), Toast.LENGTH_LONG).show();

                //Create Activity and send clicked track
            }
        });

        return view;
    }

    private void updateList() {
        ArrayAdapter lvAdapter = new ArrayAdapter(getActivity(),
                android.R.layout.simple_list_item_1, Track.listAll(Track.class));
        lvAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        binding.lvTracks.setAdapter(lvAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
