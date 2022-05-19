package com.example.memedex.pantallas.menu.coleccion;

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

import androidx.appcompat.app.AppCompatActivity;

import com.example.memedex.R;
import com.example.memedex.modelo.Meme;
import com.example.memedex.modelo.ValoresDefault;
import com.example.memedex.pantallas.menu.Menu;
import com.example.memedex.pantallas.menu.memedex.MemeRegistro;
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

public class Coleccion extends AppCompatActivity {

    private ArrayList<Meme> memes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coleccion);

        memes=new ArrayList<>();

        Button back = findViewById(R.id.back);

        pillarMemesBaseDatos();

        EditText buscar = (EditText) findViewById(R.id.finder);

        ArrayAdapter<Meme> adapter = new ArrayAdapter<Meme>(Coleccion.this, android.R.layout.simple_list_item_1,memes);
        buscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                adapter.getFilter().filter(charSequence);
                GridLayout ll = findViewById(R.id.coleccionMemes);
                ArrayList<Meme> memesBusqueda= new ArrayList<>();


                for(Meme m: memes){
                    if(m.getTitulo().toLowerCase(Locale.ROOT).contains(charSequence.toString().toLowerCase(Locale.ROOT))){
                        memesBusqueda.add(m);
                        Log.i("Memes",charSequence +" "+m.getTitulo());

                    }
                }
                ll.removeAllViews();
                for(Meme m: memesBusqueda){
                    printMeme(m);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //botón atrás
        back.setOnClickListener(view -> {
            Intent intentSign = new Intent(Coleccion.this, Menu.class);
            startActivity(intentSign);
        });


    }


    private void pillarMemesBaseDatos() {
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();

        Query query = myRef.child("Usuario")
                .child(ValoresDefault.get().getUser().getId())
                .child("coleccionMemes");
        Log.i("Memes",query.toString());

        //Query query = myRef.child("Meme");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot meme : dataSnapshot.getChildren()) {
                        memes.add(meme.getValue(Meme.class));
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
        LayoutInflater lf=LayoutInflater.from(Coleccion.this);
        View v= lf.inflate(R.layout.plantilla_memes,null);
        GridLayout ll = findViewById(R.id.coleccionMemes);
        TextView tv = v.findViewById(R.id.nombrePlantillaMeme);
        tv.setText(meme.getTitulo());
        ImageView iv = v.findViewById(R.id.imagePlantillaMeme);

        iv.setTag(meme.getTitulo());
        Log.i("Memes",iv.getTag().toString());
        Picasso.get().load(meme.getImg()).into(iv);

        ll.addView(v);
    }

    public void memeMemedex(View v){
        Intent i = new Intent(Coleccion.this, memeColeccion.class );
        i.putExtra("titulo", v.getTag().toString());
        for(Meme m: memes){
            if(v.getTag().toString().equals(m.getTitulo())) {
                i.putExtra("imgurl",m.getImg());
            }
        }
        startActivity(i);
    }
}
