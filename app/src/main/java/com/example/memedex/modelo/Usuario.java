package com.example.memedex.modelo;

public class Usuario {

    private String userName;
    private String password;
    private String email;
    private int coins;
    private int level;

    public Usuario(String userName, String password, String email){
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.coins = 0;
        this.level = 1;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", coins=" + coins +
                ", level=" + level +
                '}';
    }
}
