package com.example.boosters.Models;

public class UsuarioResponse {


    private  String Mensaje;
    private  int Estado;
    private  int codUsuario;
    private  String EsBooster;
    private  String Alias;


    public String getMensaje() {
        return Mensaje;
    }

    public void setMensaje(String mensaje) {
        Mensaje = mensaje;
    }

    public int getEstado() {
        return Estado;
    }

    public void setEstado(int estado) {
        Estado = estado;
    }

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


}
