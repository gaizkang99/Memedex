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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.memedex.R;
import com.example.memedex.modelo.Logro;
import com.example.memedex.modelo.Meme;
import com.example.memedex.modelo.ValoresDefault;
import com.example.memedex.pantallas.menu.Menu;
import com.example.memedex.pantallas.menu.coleccion.Coleccion;
import com.example.memedex.pantallas.menu.memedex.MemeRegistro;
import com.example.memedex.pantallas.menu.memedex.Memedex;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.concurrent.Exchanger;

public class Profile extends AppCompatActivity {

    private View v;
    private ArrayList<Logro> misLogros;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        misLogros= new ArrayList<>();

        ImageView perfilimg = (ImageView) findViewById(R.id.imageView1);
        ImageButton back = (ImageButton) findViewById(R.id.back);
        Button friends = (Button) findViewById(R.id.friends);

        TextView nikname = (TextView) findViewById(R.id.nikname);
        TextView level = (TextView) findViewById(R.id.level);
        nikname.setText(ValoresDefault.get().getUser().getUserName());

        level.setText("Nivel: "+String.valueOf(ValoresDefault.get().getUser().getLevel()));



        int fotodeperfil = ValoresDefault.get().getUser().getFotoperfil();
        setFotoPerfil(fotodeperfil);
        perfilimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i = ValoresDefault.get().getUser().getFotoperfil();
                i++;
                if (i==3){
                    i=0;
                }
                setFotoPerfil(i);
                ValoresDefault.get().getUser().setFotoperfil(i);
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
                Intent intentSign = new Intent(Profile.this, Exchange.class);
                startActivity(intentSign);
            }
        });

        obtenerLogrosUserFirebase();

    }

    public void setFotoPerfil(int laFoto){
        //Update
        FirebaseDatabase.getInstance().getReference("Usuario")
                .child(ValoresDefault.get().getUser().getId())
                .child("fotoperfil").setValue(laFoto);

        ImageView perfilimg = (ImageView) findViewById(R.id.imageView1);
        if (laFoto==0){
            Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/memedex-aa951.appspot.com/o/perfil1.JPG?alt=media&token=83ea862d-0d65-4407-be65-acb0b68d97bf").into(perfilimg);
        } else if (laFoto==1){
            Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/memedex-aa951.appspot.com/o/perfil3.jpg?alt=media&token=c68b1b32-6cd9-4fa8-9115-92531611d1d9").into(perfilimg);
        } else if (laFoto==2){
            Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/memedex-aa951.appspot.com/o/perfil2.png?alt=media&token=1151d2b6-23e6-4386-a9a0-c21e2679bdff").into(perfilimg);
        } else if (laFoto==3){
            Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/memedex-aa951.appspot.com/o/perfil1.JPG?alt=media&token=83ea862d-0d65-4407-be65-acb0b68d97bf").into(perfilimg);
        }
    }

    private void obtenerLogrosUserFirebase() {
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();

        Query query = myRef.child("Usuario")
                .child(ValoresDefault.get().getUser().getId())
                .child("logro");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot lograco : dataSnapshot.getChildren()) {
                        //todo- no muestra los logros de firebase del usuario
                        misLogros.add(lograco.getValue(Logro.class));
                        printLogro(lograco.getValue(Logro.class));
                    }

                }else{
                    //todo este else no funciona...
                    GridLayout ll = findViewById(R.id.memedexMemes);
                    //Text
                    TextView tv=new TextView(getApplicationContext());
                    tv.setText("¡Todavía no has conseguido ningún logro!");
                    tv.setGravity(Gravity.CENTER);

                    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                    params.addRule(RelativeLayout.BELOW, R.id.finder);
                    //ll.addView(tv, params);
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

    public void memeMemedex(View v) {
        Intent inte = new Intent(Profile.this, LogroRegistro.class);
        inte.putExtra("titulo", v.getTag().toString());
        for (Logro m : misLogros) {
            if (v.getTag().toString().equals(m.getNombre())) {
                inte.putExtra("descripcion", m.getDescripcion());
                inte.putExtra("img", m.getImg());
            }
        }
        startActivity(inte);
    }
}
