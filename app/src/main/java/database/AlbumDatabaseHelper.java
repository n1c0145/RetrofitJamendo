package database;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import models.Album;

public class AlbumDatabaseHelper extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "album.db";
    private static final int DATABASE_VERSION = 1;
    //declarar nombres de tablas y columnas
    public static final String ALBUMS_TABLE= "albums";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE="title";
    public static final String COLUMN_ARTIST="artist";
    public static final String COLUMN_GENRE="genre";
    public static final String COLUMN_IMAGE_URL="image_url";

    //CONSTRUCTOR
    public AlbumDatabaseHelper(Context context){
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(
                "CREATE TABLE " + ALBUMS_TABLE + " (" +
                        COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_TITLE + " TEXT, " +
                        COLUMN_ARTIST + " TEXT, " +
                        COLUMN_GENRE + " TEXT, " +
                        COLUMN_IMAGE_URL + " TEXT" + ");"
        );
        //primer ingreso
        ContentValues values = new ContentValues();

        values.put(COLUMN_TITLE,"Dark Side of the moon");
        values.put(COLUMN_ARTIST,"Pink Floyd");
        values.put(COLUMN_GENRE,"Rock");
        values.put(COLUMN_IMAGE_URL,"https://m.media-amazon.com/images/I/51UtWpxbNYL._UF894,1000_QL80_.jpg");

        db.insert(ALBUMS_TABLE, null, values);
        values.clear();
    }
    @Override
    public void onUpgrade(SQLiteDatabase de, int OldVersion, int newVersion){
        //implementar logica de migracion
    }

    public List<Album> getAllAlbums(){
        List<Album> albumList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = { COLUMN_ID,COLUMN_TITLE,COLUMN_IMAGE_URL,COLUMN_ARTIST,COLUMN_GENRE};

        //cursor: clase para recorrer columns y filas
        Cursor cursor = db.query(
                ALBUMS_TABLE,
                columns,
                null,
                null,
                null,
                null,
                null
        );

        if (cursor != null){
            if(cursor.moveToFirst()){
                do {
                    @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                    @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex(COLUMN_TITLE));
                    @SuppressLint("Range") String artist = cursor.getString(cursor.getColumnIndex(COLUMN_ARTIST));
                    @SuppressLint("Range") String genre = cursor.getString(cursor.getColumnIndex(COLUMN_GENRE));
                    @SuppressLint("Range") String imageUrl = cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE_URL));

                    albumList.add(new Album(title,artist,imageUrl));
                } while (cursor.moveToNext());
            }
        }
        return albumList;
    }

    public long insertAlbum(Album album){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_TITLE, album.getName());
        values.put(COLUMN_ARTIST,album.getArtistName());
        values.put(COLUMN_GENRE,"");
        values.put(COLUMN_IMAGE_URL, album.getImage());

        long id = db.insert(ALBUMS_TABLE,null,values);
        db.close();
        return id;
    }
}
