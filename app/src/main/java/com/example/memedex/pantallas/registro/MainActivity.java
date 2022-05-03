package com.example.memedex.pantallas.registro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.memedex.R;
import com.example.memedex.modelo.Meme;
import com.example.memedex.modelo.Usuario;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paginaprincipal);

        Button signIn = (Button) findViewById(R.id.signIn);
        Button login = (Button) findViewById(R.id.logIn);

/* INSERTAR MEMES
        FirebaseDatabase fb;
        fb = FirebaseDatabase.getInstance();
        DatabaseReference momardo = fb.getReference("Meme");

        momardo.push().setValue(new Meme("dam.png","Trayectoría de un curso","Este suceso es porqué cuando estás con ganas de aprender con un nuevo módulo, pero descubres que es una caca.",22,"https://firebasestorage.googleapis.com/v0/b/memedex-aa951.appspot.com/o/dam.png?alt=media&token=7deb34e5-d5d9-40cc-a4e7-0bb0137ade28"));
        momardo.push().setValue(new Meme("sistemasxd.jpg","Clases","Esto fue porqué la gente que tardaba mucho tiempo en venir a clases para hacer solamente sistemas ya que las clases posteriores fueron canceladas, ya que la persona que las daba tenia COVID.",22,"https://firebasestorage.googleapis.com/v0/b/memedex-aa951.appspot.com/o/sistemasxd.jpg?alt=media&token=2cbf2cef-af9e-4048-84b3-8ac940e68a6e"));
        momardo.push().setValue(new Meme("hectortostring.jpeg","Comienzo de OPP","Este meme surgio tras ir aprendiendo sobre la Programación orientada en objetos.",22,"https://firebasestorage.googleapis.com/v0/b/memedex-aa951.appspot.com/o/hector_toString.jpeg?alt=media&token=cb606ff1-9786-454a-ba41-648894497240"));
*/
        

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSign = new Intent(MainActivity.this, SignIn.class);
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