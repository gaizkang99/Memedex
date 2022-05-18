package com.example.memedex.modelo;

import java.util.ArrayList;

public class Usuario {

    private String userName;
    private String email;
    private int coins;
    private int level;
    //private String imgUrl;
    private ArrayList<Logro> logros;
    private ArrayList<Usuario> amigos;
    private ArrayList<Meme> memes;

    public Usuario(String userName, String email){
        this.userName = userName;
        this.email = email;
        this.coins = 240;
        this.level = 1;
        this.logros= new ArrayList<>();
        this.amigos=new ArrayList<>();
        this.memes=new ArrayList<>();
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

    public ArrayList<Meme> getMemes() {
        return memes;
    }

    public void setMemes(ArrayList<Meme> memes) {
        this.memes = memes;
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

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
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
                ", memes=" + memes +
                '}';
    }
}
