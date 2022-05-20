package com.example.memedex.modelo;


import android.os.CountDownTimer;
import android.util.Log;

import java.util.List;

public class ValoresDefault {
    public static Usuario user;
    public CountDownTimer countDownTimer;
    public float Sonido;
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
        return Sonido;
    }

    public void setSonido(float sonido) {
        Sonido = sonido;
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
