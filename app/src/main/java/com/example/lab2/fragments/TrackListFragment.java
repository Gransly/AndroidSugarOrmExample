package com.example.lab2.fragments;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.lab2.UpdateActivity;
import com.example.lab2.databinding.Frag3LayoutBinding;
import com.example.lab2.model.Track;

public class TrackListFragment extends Fragment {
    private Frag3LayoutBinding frag3LayoutBinding;


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        frag3LayoutBinding = Frag3LayoutBinding.inflate(inflater, container, false);
        View view = frag3LayoutBinding.getRoot();
        updateList();


        frag3LayoutBinding.fbtnReloadData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getActivity(), "Update", Toast.LENGTH_LONG).show();
                updateList();
            }

        });

        frag3LayoutBinding.lvTracks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Track track = (Track) parent.getAdapter().getItem(position);
                Toast.makeText(getActivity(), track.toString(), Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getActivity(), UpdateActivity.class);
                startActivity(intent);

                //Create Activity and send clicked track
            }
        });

        return view;
    }

    private void updateList() {
        ArrayAdapter lvAdapter = new ArrayAdapter(getActivity(),
                android.R.layout.simple_list_item_1, Track.listAll(Track.class));
        lvAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        frag3LayoutBinding.lvTracks.setAdapter(lvAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        frag3LayoutBinding = null;
    }
}
