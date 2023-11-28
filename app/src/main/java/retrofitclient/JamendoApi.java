package retrofitclient;

import models.JamendoAlbumResponse;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JamendoApi {
    @GET("/v3.0/albums")
    Call<JamendoAlbumResponse> getAlbums(
      @Query("client_id") String clientid,
      @Query("order") String order,
      @Query("limit") int limit,
      @Query("artist_name") String artist
    );

    public static JamendoApi getJamendoApi(){
        Retrofit retrofit = RetrofitClient.getClient();
        return retrofit.create(JamendoApi.class);
    }
    public static String getClientId(){
        return RetrofitClient.getClientId();
    }
}
