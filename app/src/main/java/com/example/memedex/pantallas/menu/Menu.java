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

public class Menu extends AppCompatActivity {

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
                            popup();
                            //handle triple tap
                        } else if (numberOfTaps == 2) {
                        }
                }

                return true;
            }


        });
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
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
        Query query= myRef
                .child("Usuario")
                .child(ValoresDefault.get().getUser().getId())
                .child("logro");

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot l : snapshot.getChildren()) {
                        if(!(l.getValue(Logro.class).getNombre().equals(logro.getNombre()))){
                            FirebaseDatabase.getInstance().getReference("Usuario")
                                    .child(ValoresDefault.get().getUser().getId())
                                    .child("logro").push().setValue(logro);
                        }
                    }
                }else{
                    FirebaseDatabase.getInstance().getReference("Usuario")
                            .child(ValoresDefault.get().getUser().getId())
                            .child("logro").push().setValue(logro);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
    }
    private void popup() {
        AlertDialog.Builder alerta = new AlertDialog.Builder(Menu.this);
        alerta.setMessage("Logro desbloqueado!!").setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog title = alerta.create();
        title.setTitle("Easter Egg!");
        title.show();
    }
}
