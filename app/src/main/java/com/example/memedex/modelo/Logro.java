package com.example.memedex.modelo;

public class Logro {

    private String nombre;
    private String descripcion;
    private String img;
    private boolean conseguido;

    public Logro(){}

    public Logro(String name, String description, String image){
        this.nombre = name;
        this.descripcion = description;
        this.img = image;
        this.conseguido = false;
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

    public boolean isConseguido() {
        return conseguido;
    }

    public void setConseguido(boolean conseguido) {
        this.conseguido = conseguido;
    }

    @Override
    public String toString() {
        return "Logro{" +
                "nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", img='" + img + '\'' +
                ", conseguido=" + conseguido +
                '}';
    }
}
