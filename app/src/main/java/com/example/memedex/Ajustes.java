package com.example.memedex;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Ajustes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajustes);

        Button atras = (Button) findViewById(R.id.atras);
        Button close = (Button) findViewById(R.id.cerrarSesion);

        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSign = new Intent(Ajustes.this, Menu.class);
                startActivity(intentSign);
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSign = new Intent(Ajustes.this, MainActivity.class);
                startActivity(intentSign);
            }
        });
    }
}
