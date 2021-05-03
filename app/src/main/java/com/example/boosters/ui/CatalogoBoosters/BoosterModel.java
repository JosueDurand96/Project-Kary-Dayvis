package com.example.boosters.ui.CatalogoBoosters;

import com.google.gson.annotations.SerializedName;

public class BoosterModel {
    @SerializedName("Alias")
    private  String  alias;
    @SerializedName("Medalla")
    private  String medalla;
    @SerializedName("idSteam")
    private  String idsteam;
    @SerializedName("Calificacion")
    private  String calificacion;
    @SerializedName("Telefono")
    private  String telefono;
    @SerializedName("CorreoContacto")
    private  String correo;

    @SerializedName("Imagen")
    private  String image;

    public String getImagen() {
        return image;
    }

    public void setImagen(String imagen) {
        image = imagen;
    }

    public String getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
    }

    public String getIdsteam() {
        return idsteam;
    }

    public void setIdsteam(String idsteam) {
        this.idsteam = idsteam;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getMedalla() {
        return medalla;
    }

    public void setMedalla(String medalla) {
        this.medalla = medalla;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public BoosterModel(String alias, String medalla, String idsteam, String calificacion, String correo, String telefono, String image) {
        this.alias = alias;
        this.medalla = medalla;
        this.idsteam = idsteam;
        this.calificacion = calificacion;
        this.correo = correo;
        this.telefono = telefono;
        this.image = image;
    }
}

