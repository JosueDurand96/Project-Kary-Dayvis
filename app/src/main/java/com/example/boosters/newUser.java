package com.example.boosters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.boosters.Data.ApiUtils;
import com.example.boosters.Interfaces.UsuarioAPI;
import com.example.boosters.Models.UsuarioResponse;
import com.example.boosters.Models.UsuarioRequest;
import com.example.boosters.Models.UsuarioResponse;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.io.File;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class newUser extends AppCompatActivity {
    public static final int CAMERA_PERM_CODE = 101;
    public static final int CAMERA_REQUEST_CODE = 102;
    public static final int GALLERY_REQUEST_CODE = 105;
    private TextView mResponseTv;
    private UsuarioAPI mAPIService;
    Button submitBtn, captura, escoger;
    private EditText nombre, apellido, dni, correo, usuario, clave, alias, esBooster, idSteam;
    private Switch EsBooster;
    private TextView lblIdSteam;
    private String p_Booster, p_idSteam;
    String currentPhotoPath;

    //CAPTURE PHOTO
    public static final int REQUEST_CODE = 100;
    ImageView imageViewPhoto;
    Button buttonCapturePhoto;
    String mCurrentPhotoPath;
    private static final int ALL_PERMISSIONS_RESULT = 1011;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (getApplicationContext().checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);
            }

        }

        imageViewPhoto = findViewById(R.id.imageView2);
        buttonCapturePhoto = findViewById(R.id.capture);


        buttonCapturePhoto.setOnClickListener(v -> {

            Log.d("josue","1");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                Log.d("josue","2");
                if (ContextCompat.checkSelfPermission(getApplicationContext(),
                        Manifest.permission.CAMERA)
                        == PackageManager.PERMISSION_GRANTED) {
                    Log.d("josue","3");
                    openCamera();
                }
            } else {
                Log.d("josue","4");
                openCamera();
            }
        });



        Switch EsBoosterR = findViewById(R.id.EsBooster);
        idSteam = findViewById(R.id.txtIdSteam);
        lblIdSteam = findViewById(R.id.lblIdSteam);

        EsBoosterR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (EsBoosterR.isChecked()) { //si es Visible lo pones Gone
                    lblIdSteam.setVisibility(View.VISIBLE);
                    idSteam.setVisibility(View.VISIBLE);

                } else { // si no es Visible, lo pones
                    lblIdSteam.setVisibility(View.GONE);
                    idSteam.setVisibility(View.GONE);
                }
            }
        });


        submitBtn = findViewById(R.id.btnConfirmar);
        escoger = findViewById(R.id.choose);
       // captura = findViewById(R.id.capture);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendPost(createRequest());
            }
        });
    }


    private void openCamera() {
        Intent takePicture4 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePicture4.resolveActivity(getApplicationContext().getPackageManager()) != null) {
            startActivityForResult(takePicture4, REQUEST_CODE);
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE && data != null) {
            Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");
            imageViewPhoto.setImageBitmap(imageBitmap);
        }
    }




    public void sendPost(UsuarioRequest usuario) {

        Call<UsuarioResponse> usuarioResponseCall = ApiUtils.getUsuarioApi().RegistrarUsuario(usuario);

        usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {
            int estado;
            String mensaje;


            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                Log.d("josue","onResponse");
                if (response.isSuccessful()) {
                    estado = response.body().getEstado();
                    mensaje = response.body().getMensaje();
                    alertDialog(estado, mensaje);
                }
            }

            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {
                Log.d("josue","onFailure");
                mensaje = t.getMessage();
                alertDialog(0, mensaje);
            }
        });
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

    public UsuarioRequest createRequest() {

        EsBooster = findViewById(R.id.EsBooster);
        idSteam = findViewById(R.id.txtIdSteam);

        if (EsBooster.isChecked()) {
            p_Booster = "B";
            p_idSteam = idSteam.getText().toString();

        } else {
            p_Booster = "C";
            p_idSteam = "";
            idSteam.setText("");
        }


        nombre = findViewById(R.id.txtNombre);
        apellido = findViewById(R.id.txtApellido);
        dni = findViewById(R.id.txtDni);
        correo = findViewById(R.id.txtCorreo);
        usuario = findViewById(R.id.txtUsuario);
        clave = findViewById(R.id.txtPassword);
        alias = findViewById(R.id.txtAlias);

        final Bitmap bitmap1 = BitmapFactory.decodeByteArray(imageViewToByte(imageViewPhoto), 0, imageViewToByte(imageViewPhoto).length);
        String imagen1 = getStringImagen(bitmap1);

        UsuarioRequest usuarioRequest = new UsuarioRequest();
        usuarioRequest.setNombre(nombre.getText().toString());
        usuarioRequest.setApellido(apellido.getText().toString());
        usuarioRequest.setDni(dni.getText().toString());
        usuarioRequest.setCorreo(correo.getText().toString());
        usuarioRequest.setUsuario(usuario.getText().toString());
        usuarioRequest.setClave(clave.getText().toString());
        usuarioRequest.setAlias(alias.getText().toString());
        usuarioRequest.setEsBooster(p_Booster);
        usuarioRequest.setIdSteam(p_idSteam);
        usuarioRequest.setFoto(imagen1);

        return usuarioRequest;
    }
    //DECODIFICAR
    private String getStringImagen(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    //CONVERTIDOR
    private static byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("¿Estás seguro de cancelar el registro?")
                .setCancelable(false)
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(getApplicationContext(), Home.class));
                        //  finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


}





