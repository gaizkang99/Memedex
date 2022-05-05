package com.example.memedex.modelo;

import com.example.memedex.modelo.Logro;
import com.example.memedex.modelo.Meme;
import com.example.memedex.modelo.Usuario;

import java.util.List;

public class ValoresDefault {
    private Usuario user;

    private ValoresDefault(){
        user = new Usuario();

    }

    private static ValoresDefault mValoresDefault;
    public static ValoresDefault get(){
        if (mValoresDefault == null){
            mValoresDefault = new ValoresDefault();
        }
        return mValoresDefault;
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
