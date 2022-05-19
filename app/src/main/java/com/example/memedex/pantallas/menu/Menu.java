package com.example.memedex.pantallas.menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Menu extends AppCompatActivity {
    DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
    private ArrayList<Usuario> usuario;
    TextView info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_opciones);

        Button justes = (Button) findViewById(R.id.ajustes);
        Button profile = (Button) findViewById(R.id.profile);
        Button capturar = (Button) findViewById(R.id.buttonCapturar);
        Button memedex = (Button) findViewById(R.id.buttonMemedex);
        Button coleccion = (Button) findViewById(R.id.buttonColeccion);
        Button exchange = (Button) findViewById(R.id.buttonIntercambiar);
        Button mercado = findViewById(R.id.buttonMercado);
        Button perfil = (Button) findViewById(R.id.profile);

        if(getIntent().getStringExtra("username")!=null)
            perfil.setText(getIntent().getStringExtra("username") + " / " + getIntent().getStringExtra("level"));
        else
            perfil.setText(ValoresDefault.get().getUser().getUserName() + " / " + ValoresDefault.get().getUser().getLevel());


        justes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu.this, Ajustes.class);
                startActivity(intent);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
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

    }
}
