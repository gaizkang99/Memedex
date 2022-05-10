package com.example.memedex.pantallas.menu.memedex;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.memedex.R;
import com.example.memedex.modelo.Meme;
import com.example.memedex.pantallas.menu.Menu;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Memedex extends AppCompatActivity {

    private View v;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memedex);
/*
        LayoutInflater lf=LayoutInflater.from(Memedex.this);
        v= lf.inflate(R.layout.plantilla_memes,null);

        LinearLayout ll = findViewById(R.id.memedexMemes);
        TextView tv = v.findViewById(R.id.nombrePlantillaMeme);

        ImageView iv = v.findViewById(R.id.imagePlantillaMeme);

        ll.addView(v);*/
        Button back = findViewById(R.id.back);
        back.setOnClickListener(view -> {
            Intent intentSign = new Intent(Memedex.this, Menu.class);
            startActivity(intentSign);
        });


        pillarMemesBaseDatos();
    }

    private void pillarMemesBaseDatos() {
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();

        Query query = myRef.child("Meme");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot meme : dataSnapshot.getChildren()) {
                        printMeme(meme.getValue(Meme.class));
                    }
                }else
                    Toast.makeText(getApplicationContext(), "Error de conexión", Toast.LENGTH_LONG).show();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void printMeme(Meme meme) {
        LayoutInflater lf=LayoutInflater.from(Memedex.this);
        v= lf.inflate(R.layout.plantilla_memes,null);

        GridLayout ll = findViewById(R.id.memedexMemes);
        TextView tv = v.findViewById(R.id.nombrePlantillaMeme);
        tv.setText(meme.getTitulo());
        ImageView iv = v.findViewById(R.id.imagePlantillaMeme);
        Picasso.get().load(meme.getImg()).into(iv);

        ll.addView(v);
    }
}
