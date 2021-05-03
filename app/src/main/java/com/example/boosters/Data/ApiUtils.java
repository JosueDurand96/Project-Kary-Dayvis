package com.example.boosters.Data;

import com.example.boosters.Interfaces.UsuarioAPI;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiUtils {
    public static final String BASE_URL = "https://dtaengineermov.azurewebsites.net/";

    private static Retrofit getRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

    public static UsuarioAPI getUsuarioApi() {
        UsuarioAPI usuarioAPI = getRetrofit().create(UsuarioAPI.class);

        return  usuarioAPI;
    }


}
