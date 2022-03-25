package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.lab2.databinding.ActivityMainBinding;
import com.example.lab2.databinding.ActivityUpdateBinding;
import com.example.lab2.databinding.Frag3LayoutBinding;
import com.example.lab2.model.Album;
import com.example.lab2.model.Artist;
import com.example.lab2.model.Track;

public class UpdateActivity extends AppCompatActivity {

    ActivityUpdateBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        binding = ActivityUpdateBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        Intent intent = getIntent();
        long trackId = intent.getLongExtra("TRACK_ID", 0);
        if(trackId == 0){
            getPreviousActivity();
        }


        Track track = Track.findById(Track.class, trackId);

        binding.inputTrackName.setText(track.getName());
        binding.inputAlbumName.setText(track.getAlbum().getTitle());
        binding.inputArtistName.setText(track.getArtist().getName());

        binding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPreviousActivity();
            }
        });

        binding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                track.delete();
                getPreviousActivity();
            }
        });

        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Album album = track.getAlbum();
                Artist artist = track.getArtist();


                String trackName = binding.inputTrackName.getText().toString();
                String albumTitle = binding.inputAlbumName.getText().toString();
                String artistName = binding.inputArtistName.getText().toString();

                if(!trackName.isEmpty())
                    track.setName(trackName);

                if(!albumTitle.isEmpty())
                    album.setTitle(albumTitle);

                if(!artistName.isEmpty())
                    artist.setName(artistName);


                track.save();
                album.save();
                artist.save();
                getPreviousActivity();
            }
        });
    }

    private void getPreviousActivity() {
        Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
        startActivity(intent);
    }
}