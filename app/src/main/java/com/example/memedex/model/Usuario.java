package com.example.memedex.model;

import java.util.ArrayList;

public class Usuario {

    private int id;
    private String username;
    private String password;
    private String mail;
    private String imagen;
    private int coins;
    private int lvl;
    private ArrayList<Usuario> amigos;

    public Usuario(){}

    public Usuario(String username, String password, String mail, String imagen, int coins, int lvl){
        this.id = id;
        this.username = username;
        this.password = password;
        this.mail = mail;
        this.imagen = imagen;
        this.coins = coins;
        this.lvl = lvl;
        this.amigos = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public ArrayList<Usuario> getAmigos() {
        return amigos;
    }

    public void setAmigos(ArrayList<Usuario> amigos) {
        this.amigos = amigos;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", mail='" + mail + '\'' +
                ", imagen='" + imagen + '\'' +
                ", coins=" + coins +
                ", lvl=" + lvl +
                ", amigos=" + amigos +
                '}';
    }
}
