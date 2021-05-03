package com.example.boosters.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UsuarioRequest {
    @SerializedName("Nombre")
    private  String Nombre;
    @SerializedName("Apellido")
    private  String Apellido;
    @SerializedName("Dni")
    private  String Dni;
    @SerializedName("Correo")
    private  String Correo;
    @SerializedName("Usuario")
    private  String Usuario;
    @SerializedName("Clave")
    private  String Clave;
    @SerializedName("Alias")
    private  String Alias;
    @SerializedName("EsBooster")
    private  String EsBooster;
    @SerializedName("idSteam")
    private  String idSteam;
    @SerializedName("image")
    private  String image;

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public String getDni() {
        return Dni;
    }

    public void setDni(String dni) {
        Dni = dni;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }

    public String getClave() {
        return Clave;
    }

    public void setClave(String clave) {
        Clave = clave;
    }

    public String getAlias() {
        return Alias;
    }

    public void setAlias(String alias) {
        Alias = alias;
    }

    public String getEsBooster() {
        return EsBooster;
    }

    public void setEsBooster(String esBooster) {
        EsBooster = esBooster;
    }

    public String getIdSteam() {
        return idSteam;
    }

    public void setIdSteam(String idSteam) {
        this.idSteam = idSteam;
    }


    public String getFoto() {
        return image;
    }

    public void setFoto(String foto) {
        this.image = foto;
    }






/*public Usuario(String nombre, String apellido, String dni, String correo, String usuario, String clave, String alias, String esBooster, String idSteam) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.correo = correo;
        this.usuario = usuario;
        this.clave = clave;
        this.alias = alias;
        this.esBooster = esBooster;
        this.idSteam = idSteam;
    }



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {        this.apellido = apellido;    }
    public String getDni() {        return dni;    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getEsBooster() {
        return esBooster;
    }

    public void setEsBooster(String esBooster) {
        this.esBooster = esBooster;
    }

    public String getIdSteam() {
        return idSteam;
    }

    public void setIdSteam(String idSteam) {
        this.idSteam = idSteam;
    }


    @Override
    public String toString() {
        return "Post{" +
                " Nombre=" + nombre + '\'' +
                ", Apellido=" + apellido + '\'' +
                ", Dni=" + dni + '\'' +
                ", Correo=" + correo + '\'' +
                ", Usuario=" + usuario + '\'' +
                ", Clave=" + clave + '\'' +
                ", Alias=" + alias + '\'' +
                ", EsBooster=" + esBooster + '\'' +
                ", IdSteam=" + idSteam + '\'' +
                '}';
    }
*/

}
