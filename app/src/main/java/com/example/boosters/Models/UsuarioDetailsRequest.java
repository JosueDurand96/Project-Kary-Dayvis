package com.example.boosters.Models;

public class UsuarioDetailsRequest {

    private int Codigo;
    private String Nombre;
    private String Apellido;
    private String Dni;
    private String Contraseña;
    private String idSteam;
    private String Telefono;
    private String Ubicacion;
    private String Medalla;
    private String Correo;

    public int getCodigo() {
        return Codigo;
    }

    public void setCodigo(int codigo) {
        Codigo = codigo;
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

    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String contraseña) {
        Contraseña = contraseña;
    }

    public String getIdSteam() {
        return idSteam;
    }

    public void setIdSteam(String idSteam) {
        this.idSteam = idSteam;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getUbicacion() {
        return Ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        Ubicacion = ubicacion;
    }

    public String getMedalla() {
        return Medalla;
    }

    public void setMedalla(String medalla) {
        Medalla = medalla;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }
}
