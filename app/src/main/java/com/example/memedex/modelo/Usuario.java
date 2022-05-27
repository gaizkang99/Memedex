package com.example.memedex.modelo;

import java.util.ArrayList;

public class Usuario {

    private String userName;
    private String email;
    private int coins;
    private int level;
    private String id;
    private String registrado;

    public Usuario(String id, String userName, String email, int coins, int level){
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.coins = coins;
        this.level = level;
    }
    public Usuario() { }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getUserName() {
        return userName;
    }

    public String getRegistrado() {
        return registrado;
    }

    public void setRegistrado(String registrado) {
        this.registrado = registrado;
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
                ", level=" + level ;
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
