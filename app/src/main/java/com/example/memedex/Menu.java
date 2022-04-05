package com.example.memedex;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_opciones);

        Button ajustes = findViewById(R.id.ajustes);
        Button profile = findViewById(R.id.profile);
        Button memedex = findViewById(R.id.buttonMemedex);
        Button colection = findViewById(R.id.buttonColeccion);
        Button exchange = findViewById(R.id.buttonIntercambiar);

        ajustes.setOnClickListener(view -> {
            Intent intentSign = new Intent(Menu.this, Ajustes.class);
            startActivity(intentSign);
        });

        profile.setOnClickListener(view -> {
            Intent intentSign = new Intent(Menu.this, Profile.class);
            startActivity(intentSign);
        });

        memedex.setOnClickListener(view -> {
            Intent intentSign = new Intent(Menu.this, Memedex.class);
            startActivity(intentSign);
        });

        colection.setOnClickListener(view -> {
            Intent intentcolection = new Intent(Menu.this, Coleccion.class);
            startActivity(intentcolection);
        });

        exchange.setOnClickListener(view -> {
            Intent intentExchange = new Intent(Menu.this, Friends.class);
            startActivity(intentExchange);
        });

    }
}
