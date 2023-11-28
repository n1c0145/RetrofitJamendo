package models;

import com.google.gson.annotations.SerializedName;

public class Album {
private String name;
//mantener convencion del modelo
@SerializedName("artist_name")
private String artistName;

private String image;

    public Album(String name, String artistName, String image) {
        this.name = name;
        this.artistName = artistName;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtist_name(String artist_name) {
        this.artistName = artist_name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}


