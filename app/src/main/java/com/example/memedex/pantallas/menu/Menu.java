package com.example.memedex.pantallas.menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.memedex.pantallas.menu.ajustes.Ajustes;
import com.example.memedex.pantallas.menu.capturar.Capturar;
import com.example.memedex.pantallas.menu.perfil.Profile;
import com.example.memedex.R;

public class Menu extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_opciones);

        Button justes = (Button) findViewById(R.id.ajustes);
        Button profile = (Button) findViewById(R.id.profile);
        Button capturar = (Button) findViewById(R.id.buttonCapturar);

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
    }


}