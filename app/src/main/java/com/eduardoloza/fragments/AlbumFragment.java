package com.eduardoloza.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import models.Album;

public class AlbumFragment extends Fragment {
    private ImageView albumCover;
    private TextView albumTitle,albumArtist,albumGenre;
    private Album album;

    private AlbumFragment(Album album){
        this.album = album;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.album_item, container, false);
        albumCover = view.findViewById(R.id.albumCover);
        albumTitle = view.findViewById(R.id.albumTitle);
        albumArtist = view.findViewById(R.id.albumArtist);
        albumGenre = view.findViewById(R.id.albumGenre);

        //Uso de libreria glade para para cragar imagen url
        Glide.with(this).load(album.getImage()).into(albumCover);

        albumTitle.setText(this.album.getName());
        albumArtist.setText(this.album.getArtistName());
        albumGenre.setText("");

        return view;

    }
}

