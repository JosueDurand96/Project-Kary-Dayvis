package com.example.boosters.ui.CatalogoBoosters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.example.boosters.R;
import com.example.boosters.catalogo_boosters;
import com.example.boosters.contacto_booster;
import com.example.boosters.detalle_booster;
import com.example.boosters.ui.CatalogoBoosters.BoosterModel;

import java.io.ByteArrayOutputStream;
import java.security.PrivateKey;
import java.util.List;
import java.util.ArrayList;

public class BoosterAdapter extends RecyclerView.Adapter<BoosterAdapter.ViewHolder> {

    private List<BoosterModel> boosterModelList;
    private Context context;

    public BoosterAdapter(List<BoosterModel> lista, Context catalogo_boosters) {
        this.boosterModelList = lista;
        this.context = catalogo_boosters;
    }

    @NonNull
    @Override
    public BoosterAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fila,parent,false);
        return new ViewHolder(view);
    }
    //Deyvis


    String rutaImage = "https://www.imagenesempres.com/documents/image/";
    @Override
    public void onBindViewHolder(@NonNull BoosterAdapter.ViewHolder holder, int position) {
        // implementar picasso en el gradle
        // Picasso.get().load(rutaImage+ boosterModelList.get(position).getImagen()).into(imagen);

        BoosterModel modal = boosterModelList.get(position);
        String alias = boosterModelList.get(position).getAlias();
        String medalla = boosterModelList.get(position).getMedalla();
        String idsteam = boosterModelList.get(position).getIdsteam();
        String calificacion = boosterModelList.get(position).getCalificacion();
        String telefono = boosterModelList.get(position).getTelefono();
        String correo = boosterModelList.get(position).getCorreo();
        holder.alias.setText(alias);
        holder.medalla.setText(medalla);
        String image = boosterModelList.get(position).getImagen();

        try {
            byte[] decoded1 = android.util.Base64.decode(image, android.util.Base64.DEFAULT);
            Bitmap bitmap1 = BitmapFactory.decodeByteArray(decoded1, 0, decoded1.length);
            holder.imagen.setImageBitmap(bitmap1);

        }catch (Exception e){

        }



        holder.btn_detalle.setOnClickListener(v -> {
            Intent intent =new Intent(context, detalle_booster.class);
            intent.putExtra( "alias1", alias);
            intent.putExtra("medalla1",medalla);
            intent.putExtra("idsteam1",idsteam);
            intent.putExtra("calificacion1",calificacion);
            intent.putExtra("telefono1",telefono);
            intent.putExtra("correo1",correo);
            intent.putExtra("image",image);
            context.startActivity(intent);

        });

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

    @Override
    public int getItemCount() {
        return boosterModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView alias;
        private TextView medalla;
        private ImageView imagen;
        private Button btn_detalle;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            alias = itemView.findViewById(R.id.text3);
            medalla = itemView.findViewById(R.id.text4);
            btn_detalle = itemView.findViewById(R.id.btn_detalle);
            imagen = itemView.findViewById(R.id.imagen);

        }
    }
}
