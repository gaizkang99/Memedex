package com.example.memedex.pantallas.menu.mercado;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.memedex.R;
import com.squareup.picasso.Picasso;

public class Vender extends AppCompatActivity {

    private Button sumarCantidad, restarCantidad;
    private Button comprar, cancelar;
    private EditText cantidad;
    private int cantidadExacta;
    private TextView nombre;
    private ImageView imagen;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vende_meme);

        sumarCantidad = findViewById(R.id.masCantidad);
        restarCantidad = findViewById(R.id.menosCantidad);
        cantidad = findViewById(R.id.numeroCantidad);
        nombre = findViewById(R.id.nombreMemeElegido);
        imagen = findViewById(R.id.imagenMemeElegido);
        comprar = findViewById(R.id.vender);
        cancelar = findViewById(R.id.cancelar);

        cantidad.setText("0");
        String nombreDeImagen = getIntent().getStringExtra("nombreMeme");
        String rutaImagen = getIntent().getStringExtra("imagenMeme");

        nombre.setText(nombreDeImagen);
        Picasso.get().load(rutaImagen).into(imagen);


        sumarCantidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cantidadExacta++;
                cantidad.setText(String.valueOf(cantidadExacta));
            }
        });
        restarCantidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cantidadExacta--;
                cantidad.setText(String.valueOf(cantidadExacta));
            }
        });
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Vender.this, Mercado.class);
                startActivity(i);
            }
        });

    }
}
