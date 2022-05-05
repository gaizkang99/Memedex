package com.example.memedex.modelo;


import java.util.List;

public class ValoresDefault {
    public static Usuario user;

    private static ValoresDefault mValoresDefault;
    public static ValoresDefault get(){
        if (mValoresDefault == null){
            mValoresDefault = new ValoresDefault();
        }
        return mValoresDefault;
    }

    public static Usuario getUser() {
        return user;
    }

    public static void setUser(Usuario user) {
        ValoresDefault.user = user;
    }

    //Esta clase sirve para no ir cargando all el rato valores de la base de datos
    /* Listado de memes existentes
     * Listado de logros existentes
     *
     * usuario
     */
    public List<Meme> memedex;
    public List<Logro> logros;

}
