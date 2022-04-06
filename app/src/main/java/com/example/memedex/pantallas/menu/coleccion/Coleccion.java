package com.example.memedex.pantallas.menu.coleccion;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.memedex.R;
import com.example.memedex.pantallas.menu.Menu;

public class Coleccion extends AppCompatActivity {

    private EditText coleccion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coleccion);

        coleccion = findViewById(R.id.finderColeccion);
        Button back = findViewById(R.id.back);


        back.setOnClickListener(view -> {
            Intent intentSign = new Intent(Coleccion.this, Menu.class);
            startActivity(intentSign);
        });

    }
}
