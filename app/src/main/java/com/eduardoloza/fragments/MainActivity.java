package com.eduardoloza.fragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import adapters.AlbumAdapter;
import models.Album;
import models.JamendoAlbumResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofitclient.JamendoApi;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AlbumAdapter albumAdapter;
    private List<Album> albums;
    private FloatingActionButton fabAlbumForm;
    private JamendoApi jamendoApi= JamendoApi.getJamendoApi();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recyclerView);
        //boton de +
        fabAlbumForm = findViewById(R.id.fabNewAlbum);
        fabAlbumForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AlbumForm.class);
                startActivity(intent);
            }
        });
        this.laodAlbums();
    }
        /*
        Albums cuando se usaba datos quemados
        albums = new ArrayList<>();
        //albums.add(new Album("The Dark Side of the moon","Pink Floyd","Rock",R.drawable.icon));
        //albums.add(new Album("Nevermind","Nirvana","Rock",R.drawable.icon2));
        //albums.add(new Album("Appetite for Destruction","Guns N' Roses","Rock",R.drawable.icon3));
        //albums.add(new Album("Back in Black","AC/DC","Rock",R.drawable.icon4));
        //albums.add(new Album("White Album","The Beatles","Rock",R.drawable.icon5));
        //albums.add(new Album("Nine Lives","Aerosmith","Rock",R.drawable.icon6));
        //albums.add(new Album("Led Zeppelin IV","Led Zeppelin","Rock",R.drawable.icon7));
        //albums.add(new Album("Some Girls","The Rolling Stones","Rock",R.drawable.icon8));
        //albums.add(new Album("Destroyer","Kiss","Rock",R.drawable.icon9));
        //albums.add(new Album("Black Album","Metallica","Rock",R.drawable.icon10));

        albumAdapter=new AlbumAdapter(albums,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(albumAdapter);*/

    //Executa cuando la pantalla se refresca
    @Override
    protected void onResume(){
        super.onResume();
        this.laodAlbums();
    }
    /**
     //Carga albums
     **/
    private void laodAlbums(){
        albums = new ArrayList<>();
        Call<JamendoAlbumResponse> call = jamendoApi.getAlbums(JamendoApi.getClientId(),
                "popularity_month",20, "Townhouse Woods");
        call.enqueue(new Callback<JamendoAlbumResponse>() {
            @Override
            public void onResponse(Call<JamendoAlbumResponse> call, Response<JamendoAlbumResponse> response) {

                if (response.isSuccessful()){
                    JamendoAlbumResponse jamendoAlbumResponse = response.body();
                    if (jamendoAlbumResponse!=null && jamendoAlbumResponse.getAlbums() != null){
                        albums = jamendoAlbumResponse.getAlbums();
                        //Despliego en pantalla
                        displayList();
                    }else{
                        showToast("Error de formato del servidor");
                    }
                }else{
                    showToast("Error de respuesta servidor");
                }

            }

            @Override
            public void onFailure(Call<JamendoAlbumResponse> call, Throwable t) {
                showToast("Error de conexion");
            }
        });

    }

    private void displayList(){
        albumAdapter = new AlbumAdapter(albums,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(albumAdapter);
    }

    /**
     * Mensaje de error
     * @param message
     */
    private void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT);

    }
}
