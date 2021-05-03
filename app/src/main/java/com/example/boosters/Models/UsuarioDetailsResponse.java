package com.example.boosters.Models;

public class UsuarioDetailsResponse {

    public int codUsuario;
    private String EsBooster;
    private String Alias;
    private String Nombre;
    private String Apellido;
    private String Dni;
    public String CorreoRegistro;
    public String idSteam;
    public String Medalla;
    public String Calificacion;
    public String Telefono;
    public String CorreoContacto;
    public String Ubicacion;


    public int getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(int codUsuario) {
        this.codUsuario = codUsuario;
    }

    public String getEsBooster() {
        return EsBooster;
    }

    public void setEsBooster(String esBooster) {
        EsBooster = esBooster;
    }

    public String getAlias() {
        return Alias;
    }

    public void setAlias(String alias) {
        Alias = alias;
    }

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

    public String getCorreoRegistro() {
        return CorreoRegistro;
    }

    public void setCorreoRegistro(String correoRegistro) {
        CorreoRegistro = correoRegistro;
    }

    public String getIdSteam() {
        return idSteam;
    }

    public void setIdSteam(String idSteam) {
        this.idSteam = idSteam;
    }

    public String getMedalla() {
        return Medalla;
    }

    public void setMedalla(String medalla) {
        Medalla = medalla;
    }

    public String getCalificacion() {
        return Calificacion;
    }

    public void setCalificacion(String calificacion) {
        Calificacion = calificacion;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getCorreoContacto() {
        return CorreoContacto;
    }

    public void setCorreoContacto(String correoContacto) {
        CorreoContacto = correoContacto;
    }

    public String getUbicacion() {
        return Ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        Ubicacion = ubicacion;
    }
}
