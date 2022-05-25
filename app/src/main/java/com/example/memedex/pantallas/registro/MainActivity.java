package com.example.memedex.pantallas.registro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

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

        ImageButton ee = (ImageButton) findViewById(R.id.imageButton2);
        Button signIn = (Button) findViewById(R.id.signIn);
        Button login = (Button) findViewById(R.id.logIn);

        //AudioService = MediaPlayer.create(this, R.raw._uno);

 //INSERTAR MEMES
/*
        FirebaseDatabase fb;
        fb = FirebaseDatabase.getInstance();
        DatabaseReference momardo = fb.getReference("Meme");

        momardo.push().setValue(new Meme("dam.png",
                "Trayectoría de un curso",
                "DAM 2T",
                "Este suceso es porqué cuando estás con ganas de aprender con un nuevo módulo, pero descubres que es una caca.",
                100,
                "https://firebasestorage.googleapis.com/v0/b/memedex-aa951.appspot.com/o/dam.png?alt=media&token=7deb34e5-d5d9-40cc-a4e7-0bb0137ade28"));
        momardo.push().setValue(new Meme("sistemasxd.jpg",
                "Clases",
                "DAM 2T",
                "Esto fue porqué la gente que tardaba mucho tiempo en venir a clases para hacer solamente sistemas ya que las clases posteriores fueron canceladas, ya que la persona que las daba tenia COVID.",
                250,
                "https://firebasestorage.googleapis.com/v0/b/memedex-aa951.appspot.com/o/sistemasxd.jpg?alt=media&token=2cbf2cef-af9e-4048-84b3-8ac940e68a6e"));
        momardo.push().setValue(new Meme("hectortostring.jpeg",
                "Comienzo de OPP",
                "DAM 2T",
                "Este meme surgio tras ir aprendiendo sobre la Programación orientada en objetos.",
                150,
                "https://firebasestorage.googleapis.com/v0/b/memedex-aa951.appspot.com/o/hector_toString.jpeg?alt=media&token=cb606ff1-9786-454a-ba41-648894497240"));
       momardo.push().setValue(new Meme("codigoFunciona.jpg",
                "De novato a experto",
                "PROGRAMADOR",
                "La evolución entre el novato y el experto",
                100,
                "https://firebasestorage.googleapis.com/v0/b/memedex-aa951.appspot.com/o/codigoFunciona.jpg?alt=media&token=d996613d-68cc-4c1f-9f68-b1dcba5c7baf"));
        momardo.push().setValue(new Meme("compilaMeme.jpg",
                "Problemas de compilación",
                "PROGRAMADOR",
                "Nunca se va a entender porque un programa compila o no",
                269,
                "https://firebasestorage.googleapis.com/v0/b/memedex-aa951.appspot.com/o/compilaMeme.jpg?alt=media&token=b10b03ad-3cec-4cc3-af3d-d354f8f61415"));
        momardo.push().setValue(new Meme("conquistaMeme.jpg",
                "Broma relacionada con java",
                "PROGRAMADOR",
                "Todo programador busca a su programadora ideal",
                201,
                "https://firebasestorage.googleapis.com/v0/b/memedex-aa951.appspot.com/o/conquistaMeme.png?alt=media&token=25c30830-666b-4982-b5b8-9243c5c3021e"));
        momardo.push().setValue(new Meme("ipMeme.jpg",
                "Direccion IP",
                "PROGRAMADOR",
                "Las IP privadas mejor guardarlas para uno mismo",
                153,
                "https://firebasestorage.googleapis.com/v0/b/memedex-aa951.appspot.com/o/ipMeme.jpg?alt=media&token=bfd2eeba-26c1-4904-9f25-9b1985819750"));
        momardo.push().setValue(new Meme("memePerro.jpg",
                "Perro burlador",
                "PROGRAMADOR",
                "Los chistes de programadores no son solo para desarrolladores",
                48,
                "https://firebasestorage.googleapis.com/v0/b/memedex-aa951.appspot.com/o/memePerro.jpg?alt=media&token=ed40238b-b656-48b8-b0b4-6a3e7c90ebd9"));
        momardo.push().setValue(new Meme("spiderMeme.jpg",
                "Spider love",
                "PROGRAMADOR",
                "Peter vacilando a Mary Jane",
                400,
                "https://firebasestorage.googleapis.com/v0/b/memedex-aa951.appspot.com/o/spiderMeme.png?alt=media&token=70b63118-d6f8-4860-8b54-3e5ff401590e"));
        momardo.push().setValue(new Meme("a",
                "Miriam Teams",
                "DAM 2T",
                "Trata de El Heroé que ha estado transmitiendo la palabra de Miriam a los usuarios del chat de What's up, ese heroé lo llaman Pedro el Juglar. ",
                10000,
                "https://firebasestorage.googleapis.com/v0/b/memedex-aa951.appspot.com/o/Miriam%20vs%20Pedro.jpg?alt=media&token=1e693879-67e5-453f-b8c2-2f9029dbd033"));
*/
        //INSERTAR LOGROS
/*
        FirebaseDatabase fb;
        fb = FirebaseDatabase.getInstance();
        DatabaseReference logro = fb.getReference("Logro");

        logro.push().setValue(new Logro("login",
            "Logro de inicio de sesion conseguido!",
            "https://firebasestorage.googleapis.com/v0/b/memedex-aa951.appspot.com/o/login.jpg?alt=media&token=68554858-8c76-4236-b184-ff040e67369a"));

        logro.push().setValue(new Logro("easterEgg",
                        "Logro de easter egg de Memedes!",
                "https://firebasestorage.googleapis.com/v0/b/memedex-aa951.appspot.com/o/easter-egg.jpg?alt=media&token=7695185d-b0a6-412a-99d2-cb343aafdbf6"));

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
        //Doble y triple clik
        ee.setOnTouchListener(new View.OnTouchListener() {
            Handler handler = new Handler();
            int numberOfTaps = 0;
            long lastTapTimeMs = 0;
            long touchDownMs = 0;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        touchDownMs = System.currentTimeMillis();
                        break;
                    case MotionEvent.ACTION_UP:
                        handler.removeCallbacksAndMessages(null);

                        if ((System.currentTimeMillis() - touchDownMs) > ViewConfiguration.getTapTimeout()) {
                            //it was not a tap
                            numberOfTaps = 0;
                            lastTapTimeMs = 0;
                            break;
                        }
                        if (numberOfTaps > 0
                                && (System.currentTimeMillis() - lastTapTimeMs) < ViewConfiguration.getDoubleTapTimeout()) {
                            numberOfTaps += 1;
                        } else {
                            numberOfTaps = 1;
                        }
                        lastTapTimeMs = System.currentTimeMillis();
                        if (numberOfTaps == 3) {
                            Toast.makeText(getApplicationContext(), "triple", Toast.LENGTH_SHORT).show();
                            //handle triple tap
                        } else if (numberOfTaps == 2) {
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    //handle double tap
                                    Toast.makeText(getApplicationContext(), "double", Toast.LENGTH_SHORT).show();
                                }
                            }, ViewConfiguration.getDoubleTapTimeout());
                        }
                }

                return true;
            }
        });
    }
}