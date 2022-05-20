package com.example.memedex.pantallas.menu;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
        Button perfil = (Button) findViewById(R.id.profile);

        perfil.setText(ValoresDefault.get().getUser().getUserName() + " / " + ValoresDefault.get().getUser().getLevel());

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

    }
}
