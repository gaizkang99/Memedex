package com.example.memedex.pantallas.menu.mercado;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.memedex.R;
import com.example.memedex.modelo.Meme;
import com.example.memedex.pantallas.menu.Menu;
import com.example.memedex.pantallas.menu.coleccion.Coleccion;
import com.example.memedex.pantallas.menu.memedex.Memedex;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Locale;

public class Mercado extends AppCompatActivity {

    private View v;
    private ArrayList<Meme> memes;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mercado);

        Button back = findViewById(R.id.back);
        EditText finder = findViewById(R.id.finder);
        memes = new ArrayList<>();

        pillarMemesBaseDatos();


        back.setOnClickListener(view -> {
            Intent intentSign = new Intent(Mercado.this, Menu.class);
            startActivity(intentSign);
        });

        ArrayAdapter<Meme> adapter = new ArrayAdapter<Meme>(Mercado.this, android.R.layout.simple_list_item_1, memes);
        finder.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);
                GridLayout ll = findViewById(R.id.mercadoMemes);
                ArrayList<Meme> memesBusqueda = new ArrayList<>();
                for (Meme m : memes) {
                    if (m.getTitulo().toLowerCase(Locale.ROOT).contains(s.toString().toLowerCase(Locale.ROOT))) {
                        memesBusqueda.add(m);
                        Log.i("Memes", s + " " + m.getTitulo());
                    }
                }
                ll.removeAllViews();
                for (Meme m : memesBusqueda) {
                    printMeme(m);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }



    private void pillarMemesBaseDatos() {
                DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();

                Query query = myRef.child("Meme");
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot meme : dataSnapshot.getChildren()) {
                                memes.add(meme.getValue(Meme.class));
                                printMeme(meme.getValue(Meme.class));
                            }
                        } else
                            Toast.makeText(getApplicationContext(), "Error de conexión", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }

            private void printMeme(Meme meme) {
                LayoutInflater lf = LayoutInflater.from(Mercado.this);
                v = lf.inflate(R.layout.plantillalarga, null);
                GridLayout ll = findViewById(R.id.mercadoMemes);
                TextView tv = v.findViewById(R.id.nombreMeme);
                tv.setText(meme.getTitulo());
                ImageView iv = v.findViewById(R.id.imagenMeme);

                iv.setTag(meme.getTitulo());
                Log.i("Memes", iv.getTag().toString());
                Picasso.get().load(meme.getImg()).into(iv);

                ll.addView(v);
            }
        }
