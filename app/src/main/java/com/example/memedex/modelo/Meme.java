package com.example.memedex.modelo;

public class Meme {

    private String nombre;
    private String titulo;
    private String descripcion;
    private String tipo;
    private int precio;
    private String img;

    public Meme(){}

    public Meme (String nombre,String titulo,String tipo,String descripcion,int precio,String img){
        this.nombre=nombre;
        this.titulo=titulo;
        this.tipo= tipo;
        this.descripcion=descripcion;
        this.precio=precio;
        this.img=img;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Meme{" +
                "nombre='" + nombre + '\'' +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                ", img='" + img + '\'' +
                '}';
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
}
