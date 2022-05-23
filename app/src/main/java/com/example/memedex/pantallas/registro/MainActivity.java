package com.example.memedex.pantallas.registro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.memedex.R;
import com.example.memedex.modelo.Logro;
import com.example.memedex.modelo.Meme;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paginaprincipal);

        Button signIn = (Button) findViewById(R.id.signIn);
        Button login = (Button) findViewById(R.id.logIn);

 //INSERTAR MEMES
        /*
        FirebaseDatabase fb;
        fb = FirebaseDatabase.getInstance();
        DatabaseReference momardo = fb.getReference("Meme");

        momardo.push().setValue(new Meme("dam.png",
                "Trayectoría de un curso",
                "Este suceso es porqué cuando estás con ganas de aprender con un nuevo módulo, pero descubres que es una caca.",
                22,
                "https://firebasestorage.googleapis.com/v0/b/memedex-aa951.appspot.com/o/dam.png?alt=media&token=7deb34e5-d5d9-40cc-a4e7-0bb0137ade28"));
        momardo.push().setValue(new Meme("sistemasxd.jpg",
                "Clases","Esto fue porqué la gente que tardaba mucho tiempo en venir a clases para hacer solamente sistemas ya que las clases posteriores fueron canceladas, ya que la persona que las daba tenia COVID.",
                22,
                "https://firebasestorage.googleapis.com/v0/b/memedex-aa951.appspot.com/o/sistemasxd.jpg?alt=media&token=2cbf2cef-af9e-4048-84b3-8ac940e68a6e"));
        momardo.push().setValue(new Meme("hectortostring.jpeg",
                "Comienzo de OPP","Este meme surgio tras ir aprendiendo sobre la Programación orientada en objetos.",
                22,
                "https://firebasestorage.googleapis.com/v0/b/memedex-aa951.appspot.com/o/hector_toString.jpeg?alt=media&token=cb606ff1-9786-454a-ba41-648894497240"));
        momardo.push().setValue(new Meme("codigoFunciona.jpg",
                "De novato a experto",
                "La evolución entre el novato y el experto",
                22,
                "https://firebasestorage.googleapis.com/v0/b/memedex-aa951.appspot.com/o/codigoFunciona.jpg?alt=media&token=d996613d-68cc-4c1f-9f68-b1dcba5c7baf"));
        momardo.push().setValue(new Meme("compilaMeme.jpg",
                "Problemas de compilación",
                "Nunca se va a entender porque un programa compila o no",
                22,
                "https://firebasestorage.googleapis.com/v0/b/memedex-aa951.appspot.com/o/compilaMeme.jpg?alt=media&token=b10b03ad-3cec-4cc3-af3d-d354f8f61415"));
        momardo.push().setValue(new Meme("conquistaMeme.jpg",
                "Broma relacionada con java",
                "Todo programador busca a su programadora ideal",
                22,
                "https://firebasestorage.googleapis.com/v0/b/memedex-aa951.appspot.com/o/conquistaMeme.png?alt=media&token=25c30830-666b-4982-b5b8-9243c5c3021e"));
        momardo.push().setValue(new Meme("ipMeme.jpg",
                "Direccion IP",
                "Las IP privadas mejor guardarlas para uno mismo",
                22,
                "https://firebasestorage.googleapis.com/v0/b/memedex-aa951.appspot.com/o/ipMeme.jpg?alt=media&token=bfd2eeba-26c1-4904-9f25-9b1985819750"));
        momardo.push().setValue(new Meme("memePerro.jpg",
                "Perro burlador",
                "Los chistes de programadores no son solo para desarrolladores",
                22,
                "https://firebasestorage.googleapis.com/v0/b/memedex-aa951.appspot.com/o/memePerro.jpg?alt=media&token=ed40238b-b656-48b8-b0b4-6a3e7c90ebd9"));
        momardo.push().setValue(new Meme("spiderMeme.jpg",
                "Spider love",
                "Peter vacilando a Mary Jane",
                22,
                "https://firebasestorage.googleapis.com/v0/b/memedex-aa951.appspot.com/o/spiderMeme.png?alt=media&token=70b63118-d6f8-4860-8b54-3e5ff401590e"));
        momardo.push().setValue(new Meme("a",
                "Miriam Teams",
                "Trata de El Heroé que ha estado transmitiendo la palabra de Miriam a los usuarios del chat de What's up, ese heroé lo llaman Pedro el Juglar. ",
                300,
                "https://firebasestorage.googleapis.com/v0/b/memedex-aa951.appspot.com/o/Miriam%20vs%20Pedro.jpg?alt=media&token=1e693879-67e5-453f-b8c2-2f9029dbd033"));
*/
        //INSERTAR LOGROS
/*
        FirebaseDatabase fb;
        fb = FirebaseDatabase.getInstance();
        DatabaseReference logro = fb.getReference("Logro");

        logro.push().setValue(new Logro("login.jpg","Logro de inicio de sesion conseguido!","https://firebasestorage.googleapis.com/v0/b/memedex-aa951.appspot.com/o/login.jpg?alt=media&token=68554858-8c76-4236-b184-ff040e67369a"));
 */



        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSign = new Intent(MainActivity.this, Register.class);
                startActivity(intentSign);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLogin = new Intent(MainActivity.this, Login.class);
                startActivity(intentLogin);
            }
        });


    }
}