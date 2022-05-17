package com.example.memedex.pantallas.registro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.memedex.R;
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
        /*FirebaseDatabase fb;
        fb = FirebaseDatabase.getInstance();
        DatabaseReference momardo = fb.getReference("Meme");

        momardo.push().setValue(new Meme("dam.png","Trayectoría de un curso","Este suceso es porqué cuando estás con ganas de aprender con un nuevo módulo, pero descubres que es una caca.",22,"https://firebasestorage.googleapis.com/v0/b/memedex-aa951.appspot.com/o/dam.png?alt=media&token=7deb34e5-d5d9-40cc-a4e7-0bb0137ade28"));
        momardo.push().setValue(new Meme("sistemasxd.jpg","Clases","Esto fue porqué la gente que tardaba mucho tiempo en venir a clases para hacer solamente sistemas ya que las clases posteriores fueron canceladas, ya que la persona que las daba tenia COVID.",22,"https://firebasestorage.googleapis.com/v0/b/memedex-aa951.appspot.com/o/sistemasxd.jpg?alt=media&token=2cbf2cef-af9e-4048-84b3-8ac940e68a6e"));
        momardo.push().setValue(new Meme("hectortostring.jpeg","Comienzo de OPP","Este meme surgio tras ir aprendiendo sobre la Programación orientada en objetos.",22,"https://firebasestorage.googleapis.com/v0/b/memedex-aa951.appspot.com/o/hector_toString.jpeg?alt=media&token=cb606ff1-9786-454a-ba41-648894497240"));
        momardo.push().setValue(new Meme("codigoFunciona.jpg","De novato a experto", "La evolución entre el novato y el experto", 22, "https://firebasestorage.googleapis.com/v0/b/memedex-aa951.appspot.com/o/codigoFunciona.jpg?alt=media&token=984da01a-3c8c-4c35-9c03-2a2f1ac5ffd7"));
        momardo.push().setValue(new Meme("compilaMeme.jpg","Problemas de compilación","Nunca se va a entender porque un programa compila o no",22,"https://firebasestorage.googleapis.com/v0/b/memedex-aa951.appspot.com/o/compilaMeme.jpg?alt=media&token=6b7971a7-c59c-4a03-82c0-f8147f0a6bda"));
        momardo.push().setValue(new Meme("conquistaMeme.jpg","Broma relacionada con java","Todo programador busca a su programadora ideal",22,"https://firebasestorage.googleapis.com/v0/b/memedex-aa951.appspot.com/o/conquistaMeme.jpg?alt=media&token=7af5417b-875a-459e-8cba-36fbb9ebfaf7"));
        momardo.push().setValue(new Meme("ipMeme.jpg","Direccion IP","Las IP privadas mejor guardarlas para uno mismo",22,"https://firebasestorage.googleapis.com/v0/b/memedex-aa951.appspot.com/o/ipMeme.jpg?alt=media&token=817aee7a-0c40-454f-96d4-be9ac1ecc7a7"));
        momardo.push().setValue(new Meme("memePerro.jpg","Perro burlador","Los chistes de programadores no son solo para desarrolladores",22,"https://firebasestorage.googleapis.com/v0/b/memedex-aa951.appspot.com/o/memePerro.jpg?alt=media&token=ed40238b-b656-48b8-b0b4-6a3e7c90ebd9"));
        momardo.push().setValue(new Meme("spiderMeme.jpg","Spider love","Peter vacilando a Mary Jane",22,"https://firebasestorage.googleapis.com/v0/b/memedex-aa951.appspot.com/o/spiderMeme.jpg?alt=media&token=bd9cd7d5-3d15-4e6a-a359-28474b61231b"));
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