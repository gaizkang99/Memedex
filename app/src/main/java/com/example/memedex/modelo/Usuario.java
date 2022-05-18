package com.example.memedex.modelo;

import java.util.ArrayList;

public class Usuario {

    private String userName;
    private String email;
    private int coins;
    private int level;
    private String id;
    //private String imgUrl;
    private ArrayList<Logro> logros;
    private ArrayList<Usuario> amigos;
    private ArrayList<Meme> coleccionMemes;
    private ArrayList<Meme> memedexMemes;

    public Usuario(String id, String userName, String email){
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.coins = 240;
        this.level = 1;
        this.logros= new ArrayList<>();
        this.amigos=new ArrayList<>();
        this.coleccionMemes=new ArrayList<>();
        this.memedexMemes=new ArrayList<>();
    }

    public ArrayList<Meme> getColeccionMemes() {
        return coleccionMemes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setColeccionMemes(ArrayList<Meme> coleccionMemes) {
        this.coleccionMemes = coleccionMemes;
    }

    public ArrayList<Meme> getMemedexMemes() {
        return memedexMemes;
    }

    public void setMemedexMemes(ArrayList<Meme> memedexMemes) {
        this.memedexMemes = memedexMemes;
    }

    public Usuario() {

    }

    public ArrayList<Logro> getLogros() {
        return logros;
    }

    public void setLogros(ArrayList<Logro> logros) {
        this.logros = logros;
    }

    public ArrayList<Usuario> getAmigos() {
        return amigos;
    }

    public void setAmigos(ArrayList<Usuario> amigos) {
        this.amigos = amigos;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCoins() {
        return coins;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", coins=" + coins +
                ", level=" + level +
                ", logros=" + logros +
                ", amigos=" + amigos +
                ", coleccionMemes=" + coleccionMemes +
                ", memedexMemes=" + memedexMemes +
                '}';
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

}
