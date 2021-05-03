package com.example.boosters;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;

import android.widget.Toast;

import com.example.boosters.Data.ApiUtils;
import com.example.boosters.Models.LoginRequest;
import com.example.boosters.Models.LoginResponse;
import com.example.boosters.Models.UsuarioDetailsRequest;
import com.example.boosters.Models.UsuarioRequest;
import com.example.boosters.Models.UsuarioResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class login extends AppCompatActivity {
    public static final String codigoP = "";

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        startActivity(new Intent(getApplicationContext(), Home.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void Cancelar(View v) {
        finish();
    }


    public void login(View v) {


        EditText txtUsuario = (EditText) findViewById(R.id.txtUsuariologin);
        EditText txtPassword = (EditText) findViewById(R.id.txtPasswordLogin);

        String usuario = txtUsuario.getText().toString();
        String password = txtPassword.getText().toString();

        sendPost(createRequest(), v);

    }

    public void sendPost(LoginRequest usuario, View v) {
        Call<LoginResponse> usuarioResponseCall = ApiUtils.getUsuarioApi().Login(usuario);
        usuarioResponseCall.enqueue(new Callback<LoginResponse>() {
            int estado;
            String mensaje;

            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {

                    String codigop = response.body().getCodigo();
                    String nombrep = response.body().getNombre();
                    String apellidop = response.body().getApellido();

                    SharedPreferences prefs = getSharedPreferences("shared_login_data", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();

                    editor.putString("codigoP", codigop);
                    editor.putString("nombreP", nombrep);
                    editor.putString("apellidoP", apellidop);
                    editor.commit();






                    if (codigop.equals("0")) {
                        alertDialog(0, "Credenciales incorrectas");
                    } else {
                        startActivity(new Intent(getApplicationContext(), WelcomeActivity.class));
                    }


                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                mensaje = t.getMessage();
                alertDialog(0, mensaje);
            }
        });
    }

    public LoginRequest createRequest() {

        //  private EditText nombre, apellido, dni, clave, idSteam, telefono, ubicacion, medalla, correo;
        EditText usuario, password;

        usuario = findViewById(R.id.txtUsuariologin);
        password = findViewById(R.id.txtPasswordLogin);


        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsuario(usuario.getText().toString());
        loginRequest.setPassword(password.getText().toString());

        return loginRequest;
    }


    private void alertDialog(int estado, String mensaje) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage(mensaje);
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,
                                int which) {
                if (estado == 1) {
                    startActivity(new Intent(getApplicationContext(), login.class));
                }
            }
        });
        AlertDialog dialog1 = dialog.create();
        dialog1.show();
    }


}