package org.example.Trello.model;

public class Tarjeta {
    private int idTarjeta;
    private String titulo;
    private String descripcion;
    private int idColumna;
    private int posicion;

    public int getPosicion(){
        return posicion;
    }
    public void setPosicion(int posicion){
        this.posicion = posicion;
    }

    public int getIdTarjeta() {
        return idTarjeta;
    }

    public void setIdTarjeta(int idTarjeta) {
        this.idTarjeta = idTarjeta;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdColumna() {
        return idColumna;
    }

    public void setIdColumna(int idColumna) {
        this.idColumna = idColumna;
    }

    @Override
    public String toString() {
        return "Tarjeta{" +
                "idTarjeta=" + idTarjeta +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", idColumna=" + idColumna +
                ", posicion=" + posicion +
                '}';
    }

}
