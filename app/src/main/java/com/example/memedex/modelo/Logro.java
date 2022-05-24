package com.example.memedex.modelo;

public class Logro {

    private String nombre;
    private String descripcion;
    private String img;

    public Logro(){}

    public Logro(String name, String description, String image){
        this.nombre = name;
        this.descripcion = description;
        this.img = image;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Logro{" +
                "nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}
