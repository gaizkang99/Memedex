package com.example.memedex.modelo;


import android.os.CountDownTimer;
import android.util.Log;

import java.util.List;

public class ValoresDefault {
    public static Usuario user;

    private static ValoresDefault ValoresDefault;

    public static ValoresDefault get(){
        if (ValoresDefault == null){
            ValoresDefault = new ValoresDefault();
        }
        return ValoresDefault;
    }

    //Esta clase sirve para no ir cargando all el rato valores de la base de datos
    /* Listado de memes existentes
     * Listado de logros existentes
     *
     * usuario
     */
    public List<Meme> memedex;
    public List<Logro> logros;
    public CountDownTimer countDownTimer;
    public float sonido;
    public float musica;

    public float getMusica() {
        return musica;
    }

    public void setMusica(float musica) {
        this.musica = musica;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public List<Meme> getMemedex() {
        return memedex;
    }

    public void setMemedex(List<Meme> memedex) {
        this.memedex = memedex;
    }

    public List<Logro> getLogros() {
        return logros;
    }

    public void setLogros(List<Logro> logros) {
        this.logros = logros;
    }

    public float getSonido() {
        return sonido;
    }

    public void setSonido(float sonido) {
        this.sonido = sonido;
    }

    public CountDownTimer getCountDownTimer() {
        return countDownTimer;
    }

    public void setCountDownTimer(CountDownTimer countDownTimer) {
        this.countDownTimer = countDownTimer;
    }


}
