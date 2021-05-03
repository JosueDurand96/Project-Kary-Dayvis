package com.example.boosters.Models;

public class UsuarioGenericResponse {

    public String Codigo;
    private String Resultado;
    private String DetalleError;

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String codigo) {
        Codigo = codigo;
    }

    public String getResultado() {
        return Resultado;
    }

    public void setResultado(String resultado) {
        Resultado = resultado;
    }

    public String getDetalleError() {
        return DetalleError;
    }

    public void setDetalleError(String detalleError) {
        DetalleError = detalleError;
    }
}
