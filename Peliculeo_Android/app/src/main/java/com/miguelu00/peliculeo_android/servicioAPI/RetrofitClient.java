package com.miguelu00.peliculeo_android.servicioAPI;

import com.miguelu00.peliculeo_android.CFG_APP;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient implements CFG_APP {
    private static final String BASE_URL = "http://" + SERVER + ":" + PUERTO_HOST + "/api/"; // Cambia por la URL base de tu API
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}