package com.example.boosters.ui.CatalogoBoosters;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BoosterInterface {
    @GET("api/v1/usuario/")
      Call<ArrayList<BoosterModel>> getAllCourses();
}

