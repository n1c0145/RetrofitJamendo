package com.eduardoloza.fragments;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import database.AlbumDatabaseHelper;
import models.Album;

public class AlbumForm extends AppCompatActivity {

    private EditText titleEditText,artistEditText,genreEditText,imageUrlEditText;
    private Button btnSave;
    private AlbumDatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_form);

        titleEditText = findViewById(R.id.title);
        artistEditText = findViewById(R.id.artist);
        genreEditText = findViewById(R.id.genre);
        imageUrlEditText = findViewById(R.id.imageUrl);
        btnSave = findViewById(R.id.saveButton);

        //instancia llamar funcion
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveAlbum();
            }
        });

        databaseHelper = new AlbumDatabaseHelper(this);

    }

    /**
     * Validra url
     * @param url
     * @return
     */
    private boolean isValidUrl(String url){
        return url != null && (url.startsWith("http://") || url.startsWith("https://"));
    }

    /**
     * guardar album en bd
     */
    private void saveAlbum(){
        String title = titleEditText.getText().toString();
        String artist = artistEditText.getText().toString();
        String genre = genreEditText.getText().toString();
        String imageUrl = imageUrlEditText.getText().toString();

        if (title.isEmpty() || artist.isEmpty() || genre.isEmpty() || imageUrl.isEmpty()){
            Toast.makeText(this,"Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
        } else if (!isValidUrl(imageUrl)) {
            Toast.makeText(this,"La url no es valida", Toast.LENGTH_SHORT).show();

        } else {
            Album album = new Album(title,artist,imageUrl);
            //crea el album
            databaseHelper.insertAlbum(album);
            Toast.makeText(this,"El album se grabo correctamnete", Toast.LENGTH_SHORT).show();
            finish();
        }

    }
}