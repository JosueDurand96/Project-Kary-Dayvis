package com.example.boosters;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class detalle_booster extends AppCompatActivity {
    String telefono;
    ImageView imageView8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_booster);
        String alias = getIntent().getExtras().getString("alias1");
        String medallas = getIntent().getExtras().getString("medalla1");
        String idsteam = getIntent().getExtras().getString("idsteam1");
        String calificacion = getIntent().getExtras().getString("calificacion1");
        String correo = getIntent().getExtras().getString("correo1");
        String image = getIntent().getExtras().getString("image");
        telefono = getIntent().getExtras().getString("telefono1");


        TextView txtalias = findViewById(R.id.alias1);
        TextView txtmedalla = findViewById(R.id.medalla1);
        TextView txtidsteam = findViewById(R.id.textView21);
        TextView txtcalificacion = findViewById(R.id.textView20);
        TextView txtcorreo = findViewById(R.id.textView65);
        TextView txttelefono = findViewById(R.id.textView67);

        imageView8 = findViewById(R.id.imageView8);

        try {

            byte[] decoded1 = android.util.Base64.decode(image, android.util.Base64.DEFAULT);
            Bitmap bitmap1 = BitmapFactory.decodeByteArray(decoded1, 0, decoded1.length);
            imageView8.setImageBitmap(bitmap1);

        }catch (Exception e){

        }


        txtalias.setText(alias);
        txtmedalla.setText(medallas);
        txtidsteam.setText(idsteam);
        txtcalificacion.setText(calificacion);
        txtcorreo.setText(correo);
        txttelefono.setText(telefono);


    }



    public void Partida(View v){
        Intent i = new Intent(this, detalle_partidas.class);
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

    public void Llamada(View view) {
        String phoneNo =  telefono;
        if (!TextUtils.isEmpty(phoneNo)) {
            String dial = "tel:" + phoneNo;
            startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial)));
        }
    }

}
