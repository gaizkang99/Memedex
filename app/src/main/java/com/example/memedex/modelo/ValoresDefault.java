package com.example.memedex.modelo;


import android.os.CountDownTimer;

public class ValoresDefault {
    public Usuario user;
    public CountDownTimer countDownTimer;
    public float sonido;
    public float Musica;

    private static ValoresDefault ValoresDefault;

    public static ValoresDefault get(){
        if (ValoresDefault == null){
            ValoresDefault = new ValoresDefault();
        }
        return ValoresDefault;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public CountDownTimer getCountDownTimer() {
        return countDownTimer;
    }

    public void setCountDownTimer(CountDownTimer countDownTimer) {
        this.countDownTimer = countDownTimer;
    }

    public float getSonido() {
        return sonido;
    }

    public void setSonido(float sonido) {
        this.sonido = sonido;
    }

    public float getMusica() {
        return Musica;
    }

    public void setMusica(float musica) {
        Musica = musica;
    }
//Esta clase sirve para no ir cargando all el rato valores de la base de datos
    /* Listado de memes existentes
     * Listado de logros existentes
     *
     * usuario
     */

}
