package com.example.memedex.pantallas.menu.memedex;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.memedex.R;
import com.example.memedex.modelo.Meme;
import com.example.memedex.modelo.ValoresDefault;
import com.example.memedex.pantallas.menu.Menu;
import com.example.memedex.pantallas.menu.perfil.Exchange;
import com.example.memedex.pantallas.registro.Login;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;

public class Memedex extends AppCompatActivity {

    private View v;
    private ArrayList<Meme> memes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memedex);

        obtenerMemesFirebase();

        //Inicialización de varaibles
        memes = new ArrayList<>();

        EditText buscar = (EditText) findViewById(R.id.finder);

        ArrayAdapter<Meme> adapter = new ArrayAdapter<Meme>(Memedex.this, android.R.layout.simple_list_item_1,memes);
        buscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                adapter.getFilter().filter(charSequence);
                GridLayout ll = findViewById(R.id.memedexMemes);
                ArrayList<Meme> memesBusqueda= new ArrayList<>();


                for(Meme m: memes){
                    if(m.getTitulo().toLowerCase(Locale.ROOT).contains(charSequence.toString().toLowerCase(Locale.ROOT))){
                        Log.i("Memes",charSequence +" "+m.getTitulo());
                        memesBusqueda.add(m);
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
        //Botones
        Button back = findViewById(R.id.back);
        back.setOnClickListener(view -> {
            Intent intentSign = new Intent(Memedex.this, Menu.class);
            startActivity(intentSign);
        });


    }

    private void obtenerMemesFirebase() {
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();

        Query query = myRef.child("Usuario")
                .child(ValoresDefault.get().getUser().getId())
                .child("memedexMemes");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot meme : dataSnapshot.getChildren()) {
                        memes.add(meme.getValue(Meme.class));
                        printMeme(meme.getValue(Meme.class));
                    }
                }else{

                    GridLayout ll = findViewById(R.id.memedexMemes);
                    //Text
                    TextView tv=new TextView(getApplicationContext());
                    tv.setText("¡Todavía te queda un montón de memes qué explorar!");
                    tv.setGravity(Gravity.CENTER);

                    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                    params.addRule(RelativeLayout.BELOW, R.id.finder);
                    ll.addView(tv, params);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Error de conexión", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void printMeme(Meme meme) {
        LayoutInflater lf=LayoutInflater.from(Memedex.this);
        v = lf.inflate(R.layout.plantilla_memes,null);
        GridLayout ll = findViewById(R.id.memedexMemes);
        TextView tv = v.findViewById(R.id.nombrePlantillaMeme);
        tv.setText(meme.getTitulo());
        ImageView iv = v.findViewById(R.id.imagePlantillaMeme);

        iv.setTag(meme.getTitulo());
        Log.i("Memes",iv.getTag().toString());
        Picasso.get().load(meme.getImg()).into(iv);

        ll.addView(v);
    }
    public void memeMemedex(View v){
        Intent i = new Intent(Memedex.this, MemeRegistro.class );
        i.putExtra("titulo", v.getTag().toString());
        for(Meme m: memes){
            if(v.getTag().toString().equals(m.getTitulo())) {
                i.putExtra("descripcion", m.getDescripcion());
                i.putExtra("img",m.getImg());
            }
        }
        startActivity(i);
    }
}
