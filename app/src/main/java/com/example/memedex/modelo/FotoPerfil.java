package com.example.memedex.modelo;

public class FotoPerfil {
    private int num;
    private String url;

    public FotoPerfil(String url) {
        this.url=url;
    }
    public FotoPerfil(){}

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "FotoPerfil{" +
                "url='" + url + '\'' +
                '}';
    }
}
