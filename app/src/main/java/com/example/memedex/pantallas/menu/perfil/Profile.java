package com.example.memedex.pantallas.menu.perfil;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.memedex.R;
import com.example.memedex.modelo.Logro;
import com.example.memedex.modelo.Meme;
import com.example.memedex.modelo.ValoresDefault;
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

public class Profile extends AppCompatActivity {

    private View v;
    private ArrayList<Logro> logros;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        ImageView perfilimg = (ImageView) findViewById(R.id.imageView1);
        Button back = (Button) findViewById(R.id.back);
        Button friends = (Button) findViewById(R.id.friends);

        logros = new ArrayList<>();

        perfilimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //OPCION DE CAMBIAR LA IMAGEN DE PERFIL AL CLICKARLA
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSign = new Intent(Profile.this, Menu.class);
                startActivity(intentSign);
            }
        });

        friends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSign = new Intent(Profile.this, Friends.class);
                startActivity(intentSign);
            }
        });

        obtenerLogrosFirebase();

    }

    private void obtenerLogrosFirebase() {

            DatabaseReference meme = FirebaseDatabase.getInstance().getReference();

            Query query = meme.child("Logro");
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        for (DataSnapshot logro : dataSnapshot.getChildren()) {
                            logros.add(logro.getValue(Logro.class));
                            Log.i("Memes",logros.toString());
                            printLogro(logro.getValue(Logro.class));
                        }
                    }
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Toast.makeText(getApplicationContext(), "Error de conexión", Toast.LENGTH_LONG).show();
                }
            });

    }

    private void printLogro(Logro logro) {
        LayoutInflater lf=LayoutInflater.from(Profile.this);
        v = lf.inflate(R.layout.plantilla_memes,null);

        //dimensiones de pantalla
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = (int) (metrics.widthPixels); // ancho absoluto en pixels
        int height = (int) (metrics.heightPixels); // alto absoluto en pixels

        RelativeLayout.LayoutParams params =
                new RelativeLayout.LayoutParams(
                        width/3,
                        height/5);

        GridLayout ll = findViewById(R.id.mislogros);
        TextView tv = v.findViewById(R.id.nombrePlantillaMeme);
        tv.setText(logro.getNombre());
        ImageView iv = v.findViewById(R.id.imagePlantillaMeme);

        iv.setTag(logro.getNombre());
        Picasso.get().load(logro.getImg()).into(iv);

        ll.addView(v,params);
    }

}
