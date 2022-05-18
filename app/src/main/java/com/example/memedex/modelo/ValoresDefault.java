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

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
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

}
