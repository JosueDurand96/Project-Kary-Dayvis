package com.example.boosters.ui.EditUsuario;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.boosters.Data.ApiUtils;
import com.example.boosters.Home;
import com.example.boosters.Interfaces.UsuarioAPI;
import com.example.boosters.Models.UsuarioDetailsRequest;
import com.example.boosters.Models.UsuarioDetailsResponse;
import com.example.boosters.Models.UsuarioGenericResponse;
import com.example.boosters.Models.UsuarioResponse;
import com.example.boosters.R;
import com.example.boosters.catalogo_boosters;
import com.example.boosters.login;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EditUser extends Fragment {
    Button submitBtn;
    private EditUserViewModel mViewModel;
    private EditText nombre, apellido, dni, clave, idSteam, telefono, ubicacion, medalla, correo;
    private TextView lblUsuario;
    Button btnBuscar;
    Activity context;

    public static EditUser newInstance() {
        return new EditUser();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        context = getActivity();
        SharedPreferences prefs = this.getActivity().getSharedPreferences("shared_login_data", Context.MODE_PRIVATE);
        String codigo1 = prefs.getString("codigoP","0").toString();

        find(codigo1);
        return inflater.inflate(R.layout.edit_user_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(EditUserViewModel.class);
        // TODO: Use the ViewModel
    }

    public void onStart() {
        super.onStart();
        Button btn = (Button) context.findViewById(R.id.btnGrabar);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendPost(createRequest());
            }

        });
    }


    private void find(String codigo) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://dtaengineermov.azurewebsites.net/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        UsuarioAPI usuarioAPI = retrofit.create(UsuarioAPI.class);
        Call<UsuarioDetailsResponse> call = usuarioAPI.ObtenerBoosterById(Integer.parseInt(codigo));
        call.enqueue(new Callback<UsuarioDetailsResponse>() {
            @Override
            public void onResponse(Call<UsuarioDetailsResponse> call, Response<UsuarioDetailsResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        UsuarioDetailsResponse p = response.body();
                        telefono = getView().findViewById(R.id.txtTelefono);
                        lblUsuario = getView().findViewById(R.id.lblUsuarioT);
                        nombre = getView().findViewById(R.id.txtNombre);
                        apellido = getView().findViewById(R.id.txtApellido);
                        dni = getView().findViewById(R.id.txtDni);
                        clave = getView().findViewById(R.id.txtClave);
                        idSteam = getView().findViewById(R.id.txtIdSteam);
                        telefono = getView().findViewById(R.id.txtTelefono);
                        ubicacion = getView().findViewById(R.id.txtUbicacion);
                        medalla = getView().findViewById(R.id.txtMedalla);
                        correo = getView().findViewById(R.id.txtCorreo);

                        lblUsuario.setText(p.getAlias().toString());
                        nombre.setText(p.getNombre().toString());
                        apellido.setText(p.getApellido().toString());
                        dni.setText(p.getDni().toString());
                        idSteam.setText(p.getIdSteam().toString());
                        telefono.setText(p.getTelefono().toString());
                        ubicacion.setText(p.getUbicacion().toString());
                        medalla.setText(p.getMedalla().toString());
                        correo.setText(p.getCorreoContacto().toString());
                    }

                } catch (Exception ex) {

                    String error = ex.getMessage();

                }

            }

            @Override
            public void onFailure(Call<UsuarioDetailsResponse> call, Throwable t) {
                String error = t.getMessage();
            }
        });
    }


    public void sendPost(UsuarioDetailsRequest usuario) {
        Call<UsuarioGenericResponse> usuarioResponseCall = ApiUtils.getUsuarioApi().ActualizarDatos(usuario);
        usuarioResponseCall.enqueue(new Callback<UsuarioGenericResponse>() {
            String codigo, resultado, DetalleError;

            @Override
            public void onResponse(Call<UsuarioGenericResponse> call, Response<UsuarioGenericResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        codigo = response.body().getCodigo();
                        resultado = response.body().getResultado();
                        DetalleError = response.body().getDetalleError();
                        alertDialog(Boolean.parseBoolean(codigo.toString()), DetalleError);
                    }
                } catch (Exception ex) {

                    String error = ex.getMessage();

                }
            }

            @Override
            public void onFailure(Call<UsuarioGenericResponse> call, Throwable t) {
                alertDialog(false, t.getMessage());
            }
        });
    }

    private void alertDialog(Boolean estado, String mensaje) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this.getActivity());
        dialog.setMessage(mensaje);
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,
                                int which) {
                if (estado == true) {
                    startActivity(new Intent(getActivity().getApplicationContext(), login.class));

                }
            }
        });

        AlertDialog alertDialog = dialog.create();
        alertDialog.show();
    }

    public UsuarioDetailsRequest createRequest() {

        SharedPreferences prefs = this.getActivity().getSharedPreferences("shared_login_data", Context.MODE_PRIVATE);
        String codigo = prefs.getString("codigoP","0").toString();


        //  private EditText nombre, apellido, dni, clave, idSteam, telefono, ubicacion, medalla, correo;

        nombre = getView().findViewById(R.id.txtNombre);
        apellido = getView().findViewById(R.id.txtApellido);
        dni = getView().findViewById(R.id.txtDni);
        clave = getView().findViewById(R.id.txtClave);
        idSteam = getView().findViewById(R.id.txtIdSteam);
        telefono = getView().findViewById(R.id.txtTelefono);
        ubicacion = getView().findViewById(R.id.txtUbicacion);
        medalla = getView().findViewById(R.id.txtMedalla);
        correo = getView().findViewById(R.id.txtCorreo);

        UsuarioDetailsRequest usuarioRequest = new UsuarioDetailsRequest();
        usuarioRequest.setCodigo(Integer.parseInt(codigo));
        usuarioRequest.setNombre(nombre.getText().toString());
        usuarioRequest.setApellido(apellido.getText().toString());
        usuarioRequest.setDni(dni.getText().toString());
        usuarioRequest.setContraseña(clave.getText().toString());
        usuarioRequest.setIdSteam(correo.getText().toString());
        usuarioRequest.setTelefono(telefono.getText().toString());
        usuarioRequest.setUbicacion(ubicacion.getText().toString());
        usuarioRequest.setMedalla(medalla.getText().toString());
        usuarioRequest.setCorreo(correo.getText().toString());
        return usuarioRequest;
    }
}