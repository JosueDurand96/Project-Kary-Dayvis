package com.example.boosters.Interfaces;

import com.example.boosters.Models.LoginRequest;
import com.example.boosters.Models.LoginResponse;
import com.example.boosters.Models.UsuarioDetailsRequest;
import com.example.boosters.Models.UsuarioDetailsResponse;
import com.example.boosters.Models.UsuarioGenericResponse;
import com.example.boosters.Models.UsuarioRequest;
import com.example.boosters.Models.UsuarioRequest;
import com.example.boosters.Models.UsuarioResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UsuarioAPI {

    @POST("/api/v1/usuario")
    public Call<UsuarioResponse> RegistrarUsuario(@Body UsuarioRequest usuario);

    @POST("/api/v1/UsuarioRegistro")
    public Call<UsuarioGenericResponse> ActualizarDatos(@Body UsuarioDetailsRequest usuarioDetailsRequest);

    @GET("/api/v1/usuario/{id}")
    public Call<UsuarioDetailsResponse> ObtenerBoosterById(@Path("id") int id);

    @POST("/api/v1/login")
    public Call<LoginResponse> Login(@Body LoginRequest login);
}
