package retrofitclient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL = "https://api.jamendo.com";
    private static final String CLIENT_ID = "fd96054e";

    private static Retrofit retrofit = null;

    /**
     * Funcion de metodo para singleton de cliente api
     * @return
     */
    public static Retrofit getClient(){
        if (retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

        }
        return retrofit;
    }
    public static String getClientId(){
        return CLIENT_ID;
    }
}
