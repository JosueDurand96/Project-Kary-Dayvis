package com.example.boosters;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.boosters.ui.CatalogoBoosters.BoosterAdapter;
import com.example.boosters.ui.CatalogoBoosters.BoosterInterface;
import com.example.boosters.ui.CatalogoBoosters.BoosterModel;

import java.util.ArrayList;
import java.util.List;

public class catalogo_boosters extends AppCompatActivity {
    private RecyclerView courseRV;
    private ArrayList<BoosterModel> recyclerDataArrayList;
    private BoosterAdapter recyclerViewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo_boosters);

        courseRV = findViewById(R.id.recycler);
        Button boton = findViewById(R.id.btn_detalle);
        recyclerDataArrayList = new ArrayList<>();
        getAllCourses();
    }

    private void getAllCourses() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dtaengineermov.azurewebsites.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        BoosterInterface boosterInterface = retrofit.create(BoosterInterface.class);

        Call<ArrayList<BoosterModel>> call = boosterInterface.getAllCourses();
        call.enqueue(new Callback<ArrayList<BoosterModel>>() {
            @Override
            public void onResponse(Call<ArrayList<BoosterModel>> call, Response<ArrayList<BoosterModel>> response) {
                if (response.isSuccessful()){
                    recyclerDataArrayList = response.body();
                    for (int i = 0; i < recyclerDataArrayList.size(); i++){
                        recyclerViewAdapter = new BoosterAdapter(recyclerDataArrayList, catalogo_boosters.this);
                        LinearLayoutManager manager = new LinearLayoutManager(catalogo_boosters.this);
                        courseRV.setLayoutManager(manager);
                        courseRV.setAdapter(recyclerViewAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<BoosterModel>> call, Throwable t) {
                Toast.makeText(catalogo_boosters.this, "Fail to get Data",Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void profile1(View v){
        Intent i = new Intent(this, detalle_booster.class);
        startActivity(i);
    }
    public void Main(View v){
        Intent i = new Intent(this, catalogo_videojuegos.class);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.home) {
            Intent i = new Intent(this, Home.class);
            startActivity(i);

        }
        return true;
    }
}