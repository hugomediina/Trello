package org.example.Trello.model;

public class Columna {
    private int idColumna;
    private String nombre;
    private int idTablero;

    public int getIdColumna() {
        return idColumna;
    }

    public void setIdColumna(int idColumna) {
        this.idColumna = idColumna;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdTablero() {
        return idTablero;
    }

    public void setIdTablero(int idTablero) {
        this.idTablero = idTablero;
    }

    @Override
    public String toString() {
        return "Columna{" +
                "idColumna=" + idColumna +
                ", nombre='" + nombre + '\'' +
                ", idTablero=" + idTablero +
                '}';
    }
}
