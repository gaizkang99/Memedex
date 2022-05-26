package com.example.memedex.pantallas.menu;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.memedex.modelo.Logro;
import com.example.memedex.modelo.Usuario;
import com.example.memedex.modelo.ValoresDefault;
import com.example.memedex.pantallas.menu.perfil.Exchange;
import com.example.memedex.pantallas.menu.ajustes.Ajustes;
import com.example.memedex.pantallas.menu.capturar.Capturar;
import com.example.memedex.pantallas.menu.coleccion.Coleccion;
import com.example.memedex.pantallas.menu.memedex.Memedex;
import com.example.memedex.pantallas.menu.perfil.Profile;
import com.example.memedex.R;
import com.example.memedex.pantallas.menu.mercado.Mercado;
import com.example.memedex.pantallas.registro.Login;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Menu extends AppCompatActivity {
    int images[]={R.drawable.logo,R.drawable.perfil,R.drawable.example};
    private AlertDialog.Builder popupbuilder;
    private AlertDialog popup;
    private Button ok;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_opciones);

        ImageView perfilimg = (ImageView) findViewById(R.id.imageView1);
        Button justes = (Button) findViewById(R.id.ajustes);
        Button capturar = (Button) findViewById(R.id.buttonCapturar);
        Button memedex = (Button) findViewById(R.id.buttonMemedex);
        Button coleccion = (Button) findViewById(R.id.buttonColeccion);
        Button exchange = (Button) findViewById(R.id.buttonIntercambiar);
        Button mercado = findViewById(R.id.buttonMercado);
        Button perfil = (Button) findViewById(R.id.profile);
        Button logro2 = (Button) findViewById(R.id.logro2);

        int fotodeperfil = ValoresDefault.get().getUser().getFotoperfil();
        setFotoPerfil(fotodeperfil);

        perfil.setText(ValoresDefault.get().getUser().getUserName() + " / Nivel: " + ValoresDefault.get().getUser().getLevel());

        justes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu.this, Ajustes.class);
                startActivity(intent);
            }
        });

        perfilimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu.this, Profile.class);
                startActivity(intent);
            }
        });
        perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu.this, Profile.class);
                startActivity(intent);
            }
        });

        capturar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu.this, Capturar.class);
                startActivity(intent);
            }
        });

        memedex.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent i = new Intent(Menu.this, Memedex.class);
                startActivity(i);
            }
        });

        coleccion.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent i = new Intent(Menu.this, Coleccion.class);
                startActivity(i);
            }
        });
        exchange.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent i = new Intent(Menu.this, Exchange.class);
                startActivity(i);
            }
        });
        mercado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Menu.this, Mercado.class);
                startActivity(i);
            }
        });

        logro2.setOnTouchListener(new View.OnTouchListener() {
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
                            logro2Obtenido();
                            //handle triple tap
                        } else if (numberOfTaps == 2) {
                        }
                }

                return true;
            }


        });
    }

    private void setFotoPerfil(int fotodeperfil) {
        ImageView perfilimg = (ImageView) findViewById(R.id.imageView1);
        if (fotodeperfil==0){
            Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/memedex-aa951.appspot.com/o/perfil1.JPG?alt=media&token=83ea862d-0d65-4407-be65-acb0b68d97bf").into(perfilimg);
        } else if (fotodeperfil==1){
            Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/memedex-aa951.appspot.com/o/perfil3.jpg?alt=media&token=c68b1b32-6cd9-4fa8-9115-92531611d1d9").into(perfilimg);
        } else if (fotodeperfil==2){
            Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/memedex-aa951.appspot.com/o/perfil2.png?alt=media&token=1151d2b6-23e6-4386-a9a0-c21e2679bdff").into(perfilimg);
        } else if (fotodeperfil==3){
            Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/memedex-aa951.appspot.com/o/perfil1.JPG?alt=media&token=83ea862d-0d65-4407-be65-acb0b68d97bf").into(perfilimg);
        }
    }

    private void logro2Obtenido() {
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
        Query query= myRef
                .child("Logro")
                .orderByChild("nombre")
                .equalTo("easterEgg");

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    Logro newlogro;
                    for (DataSnapshot logru : snapshot.getChildren()) {
                        newlogro=  logru.getValue(Logro.class);
                        insertLogro(newlogro);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
    }

    private void insertLogro(Logro logro) {
        //Update level
        int w=ValoresDefault.get().getUser().getLevel()+1;
        ValoresDefault.get().getUser().setLevel(ValoresDefault.get().getUser().getLevel()+1);

        Log.i("Memes",String.valueOf(w));
        FirebaseDatabase.getInstance().getReference("Usuario")
                .child(ValoresDefault.get().getUser().getId())
                .child("level").setValue(w);

        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
        Query query= myRef
                .child("Usuario")
                .child(ValoresDefault.get().getUser().getId())
                .child("logro").orderByChild("nombre").equalTo(logro.getNombre());

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot l : snapshot.getChildren()) {
                        if(!(l.getValue(Logro.class).getNombre().equals(logro.getNombre()))){
                            FirebaseDatabase.getInstance().getReference("Usuario")
                                    .child(ValoresDefault.get().getUser().getId())
                                    .child("logro").push().setValue(logro);
                            //TODO SUBIR +1 level
                            newpopup();
                        }
                    }
                }else{
                    FirebaseDatabase.getInstance().getReference("Usuario")
                            .child(ValoresDefault.get().getUser().getId())
                            .child("logro").push().setValue(logro);
                    newpopup();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
    }

    public void newpopup(){
        popupbuilder = new AlertDialog.Builder(this);
        final View contactPopupView = getLayoutInflater().inflate(R.layout.popup,null);
        ok = (Button) contactPopupView.findViewById(R.id.buton);

        TextView texto = (TextView) findViewById(R.id.texto);
        texto.setText("Logro de easter egg de Memedes!");

        popupbuilder.setView(contactPopupView);
        popup = popupbuilder.create();
        popup.show();

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popup.dismiss();
            }
        });
    }
}
