package models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JamendoAlbumResponse {
    //Decorador para mantener nombre del json
    @SerializedName("results")
    private List<Album> albums;

    public List<Album> getAlbums() {
        return albums;
    }
}
