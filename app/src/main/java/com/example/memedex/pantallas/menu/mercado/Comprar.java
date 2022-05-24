package com.example.memedex.pantallas.menu.mercado;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.memedex.R;
import com.example.memedex.modelo.Meme;
import com.example.memedex.modelo.Usuario;
import com.example.memedex.modelo.ValoresDefault;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Comprar extends AppCompatActivity {


    private int cantidadExacta;
    private String nombreDeImagen, rutaImagen, valorMeme, descripcionMeme, nombreMeme;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.compre_meme);

        Button sumarCantidad = findViewById(R.id.masCantidad);
        Button restarCantidad = findViewById(R.id.menosCantidad);
        EditText cantidad = findViewById(R.id.numeroCantidad);
        TextView nombre = findViewById(R.id.nombreMemeElegido);
        ImageView imagen = findViewById(R.id.imagenMemeElegido);
        Button comprar = findViewById(R.id.comprar);
        Button cancelar = findViewById(R.id.cancelar);

        cantidad.setText("0");
        cantidadExacta = Integer.parseInt(cantidad.getText().toString());
        nombreDeImagen = getIntent().getStringExtra("tituloMeme");
        rutaImagen = getIntent().getStringExtra("imagenMeme");
        valorMeme = getIntent().getStringExtra("valorMeme");
        descripcionMeme = getIntent().getStringExtra("descripcionMeme");
        nombreMeme = getIntent().getStringExtra("nombreMeme");
        Log.i("Hola", "Info meme: ");
        Log.i("Hola", nombreDeImagen);
        Log.i("Hola", rutaImagen);
        Log.i("Hola", valorMeme);
        Log.i("Hola", nombreMeme);
        Log.i("Hola", descripcionMeme);

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
                Intent i = new Intent(Comprar.this, Mercado.class);
                startActivity(i);
            }
        });
        comprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compruebaMonedas(cantidadExacta);
            }
        });
    }

    public void compruebaMonedas(int cantidadDeMemes){
        int valor = Integer.parseInt(valorMeme);
        int monedasJugador = ValoresDefault.get().getUser().getCoins();

        if(cantidadDeMemes<=0){
            Toast.makeText(this,"No puedes comprar menos de 1 meme", Toast.LENGTH_SHORT).show();
        }else{
            int valorTotal = valor*cantidadDeMemes;
            if(monedasJugador>=valorTotal){
                int resultado = monedasJugador - valorTotal;
                //Update
                FirebaseDatabase.getInstance().getReference("Usuario")
                        .child(ValoresDefault.get().getUser().getId())
                        .child("coins").setValue(resultado);
                for(int i=0; i < cantidadDeMemes; i++){
                    Meme meme = new Meme(nombreMeme,nombreDeImagen,"DAM 2T",descripcionMeme,valor,rutaImagen);
                    Toast.makeText(this, "Has comprado " + String.valueOf(cantidadDeMemes) + " de memes", Toast.LENGTH_SHORT).show();
                    insertaMemeColeccion(meme);
                }
                Intent i = new Intent(Comprar.this, Mercado.class);
                startActivity(i);
            }else{
                Toast.makeText(this, "No tienes suficientes monedas para comprarlo", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void insertaMemeColeccion(Meme meme){
        FirebaseDatabase.getInstance().getReference("Usuario")
                .child(ValoresDefault.get().getUser().getId()).child("coleccionMemes")
                .push().setValue(meme);

        DatabaseReference memedex = FirebaseDatabase.getInstance().getReference()
                .child("Usuario")
                .child(ValoresDefault.get().getUser().getId())
                .child("memedexMemes");
        memedex.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean existenciaEnMemedex = false;
                if (snapshot.exists()) {
                    for (DataSnapshot m : snapshot.getChildren()) {
                        Log.i("Memes",m.getValue(Meme.class).getNombre());
                        if(m.getValue(Meme.class).getTitulo().equals(meme.getTitulo())){
                            existenciaEnMemedex=true;
                        }
                    }
                    if(!existenciaEnMemedex){
                        FirebaseDatabase.getInstance().getReference()
                                .child("Usuario")
                                .child(ValoresDefault.get().getUser().getId())
                                .child("memedexMemes").push().setValue(meme);
                    }
                }else{
                    FirebaseDatabase.getInstance().getReference()
                            .child("Usuario")
                            .child(ValoresDefault.get().getUser().getId())
                            .child("memedexMemes").push().setValue(meme);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
