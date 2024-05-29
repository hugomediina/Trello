package org.example.Trello.model;

import org.springframework.boot.autoconfigure.security.SecurityProperties;

public class Tablero {
    private int idTablero;
    private String nombre;

    private String usuario;

    public int getIdTablero() {
        return idTablero;
    }

    public void setIdTablero(int idTablero) {
        this.idTablero = idTablero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Tablero{" +
                "id_tablero=" + idTablero +
                ", nombre='" + nombre + '\'' +
                ", usuario=" + usuario +
                '}';
    }
}
