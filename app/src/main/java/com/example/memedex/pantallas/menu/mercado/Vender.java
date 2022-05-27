package com.example.memedex.pantallas.menu.mercado;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.memedex.R;
import com.example.memedex.modelo.Meme;
import com.example.memedex.modelo.ValoresDefault;
import com.example.memedex.pantallas.menu.Menu;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Vender extends AppCompatActivity {

    private int cantidadExacta;
    private String nombreDeImagen, rutaImagen, valorMeme, descripcionMeme, nombreMeme;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vende_meme);

        Button sumarCantidad = findViewById(R.id.masCantidad);
        Button restarCantidad = findViewById(R.id.menosCantidad);
        EditText cantidad = findViewById(R.id.numeroCantidad);
        TextView nombre = findViewById(R.id.nombreMemeElegido);
        ImageView imagen = findViewById(R.id.imagenMemeElegido);
        Button vender = findViewById(R.id.vender);
        Button cancelar = findViewById(R.id.cancelar);

        cantidad.setText("0");
        cantidadExacta = Integer.parseInt(cantidad.getText().toString());
        nombreDeImagen = getIntent().getStringExtra("tituloMeme");
        rutaImagen = getIntent().getStringExtra("imagenMeme");
        valorMeme = getIntent().getStringExtra("valorMeme");
        descripcionMeme = getIntent().getStringExtra("descripcionMeme");
        nombreMeme = getIntent().getStringExtra("nombreMeme");

        nombre.setText(nombreDeImagen + " // Valor: " + valorMeme);
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
                if(cantidadExacta>0)
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
        vender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compruebaColeccion(cantidadExacta);
            }
        });
    }

    public void compruebaColeccion(int cantidadExacta){
        ArrayList<Meme> coleccion = new ArrayList<>();
        ArrayList<Meme> posiblesVentas = new ArrayList<>();
        ArrayList<String> llaves = new ArrayList<>();
        ArrayList<String> llavesVentas = new ArrayList<>();

        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
        Query query = myRef.child("Usuario")
                .child(ValoresDefault.get().getUser().getId())
                .child("coleccionMemes");

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot meme : snapshot.getChildren()) {
                    coleccion.add(meme.getValue(Meme.class));
                    if(coleccion.get(coleccion.size()-1).getTitulo().equals(nombreDeImagen)){
                        posiblesVentas.add(coleccion.get(coleccion.size()-1));
                        llaves.add(meme.getKey());
                    }
                }
                venderMemes(cantidadExacta, posiblesVentas, llaves);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void venderMemes(int cantidadExacta, ArrayList<Meme> ventas, ArrayList<String> llaves){
        int valor = Integer.parseInt(valorMeme);
        int monedasJugador = ValoresDefault.get().getUser().getCoins();

        if (ventas.size()==0){
            Toast.makeText(this, "No tienes memes de este tipo", Toast.LENGTH_SHORT).show();
        }
        if(ventas.size()>=cantidadExacta){
            int valorTotal = valor*cantidadExacta;
            int resultado = monedasJugador + valorTotal;
            //Update
            ValoresDefault.get().getUser().setCoins(resultado);
            FirebaseDatabase.getInstance().getReference("Usuario")
                    .child(ValoresDefault.get().getUser().getId())
                    .child("coins").setValue(resultado);
            for (int i=0; i<cantidadExacta; i++){
                Meme meme = new Meme(nombreMeme,nombreDeImagen,"DAM 2T",descripcionMeme,valor,rutaImagen);
                FirebaseDatabase.getInstance().getReference("Usuario")
                        .child(ValoresDefault.get().getUser().getId())
                        .child("coleccionMemes").child(llaves.get(i))
                        .removeValue();
                ventas.remove(meme);
            }
            Toast.makeText(this, "Has Vendido " + cantidadExacta + " de memes", Toast.LENGTH_SHORT).show();

            Intent i = new Intent(Vender.this, Mercado.class);
            startActivity(i);
        }else{
            Toast.makeText(this, "No tienes tantos memes de este tipo", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK) {
            Intent i = new Intent(this, Mercado.class);
            startActivity(i);
        }
        return super.onKeyDown(keyCode, event);
    }
}
