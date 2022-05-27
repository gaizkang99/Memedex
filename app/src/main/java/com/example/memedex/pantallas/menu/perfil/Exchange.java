package com.example.memedex.pantallas.menu.perfil;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.memedex.R;
import com.example.memedex.modelo.Usuario;
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

public class Exchange extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exchange);

        ImageButton back = (ImageButton) findViewById(R.id.back);
        Button addFriend = (Button) findViewById(R.id.addFriend);

        obtenerAmigos();
        obtenerPeticionesAmigos();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSign = new Intent(Exchange.this, Menu.class);
                startActivity(intentSign);
            }
        });

        addFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder alerta = new AlertDialog.Builder(Exchange.this);
                EditText text = new EditText(Exchange.this);
                text.setGravity(Gravity.CENTER);

                alerta.setMessage("Inserta el nombre del usuario que quieres añadir a amigos:")
                        .setCancelable(true)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (!text.getText().toString().equals(ValoresDefault.get().getUser().getUserName()))
                                    sendPeticion(text.getText().toString());
                                else
                                    Toast.makeText(Exchange.this, "No puedes agregarte a ti mismo", Toast.LENGTH_SHORT).show();

                                dialogInterface.cancel();
                            }
                        });
                AlertDialog title = alerta.create();
                title.setTitle("Añadir amigos");
                text.setHint("username");
                title.setView(text);
                title.show();
            }
        });

    }


    private void sendPeticion(String username) {
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
        Query query = myRef.child("Usuario")
                .orderByChild("userName")
                .equalTo(username);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot s : snapshot.getChildren()) {
                        Usuario u = s.getValue(Usuario.class);
                        Query query1 = myRef.child("Usuario")
                                .child(u.getId())
                                .child("amigos")
                                .orderByChild("userName")
                                .equalTo(ValoresDefault.get().getUser().getUserName());

                        query1.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if(snapshot.exists()){
                                    boolean si=false;
                                    for(DataSnapshot w : snapshot.getChildren()){
                                        si=true;
                                    }
                                    if(!si){
                                        DatabaseReference dr = FirebaseDatabase.getInstance().getReference("Usuario")
                                                .child(u.getId())
                                                .child("amigos").push();

                                        Usuario user = new Usuario();
                                        user.setFotoperfil(ValoresDefault.get().getUser().getFotoperfil());
                                        user.setCoins(ValoresDefault.get().getUser().getCoins());
                                        user.setEmail(ValoresDefault.get().getUser().getEmail());
                                        user.setUserName(ValoresDefault.get().getUser().getUserName());
                                        user.setLevel(ValoresDefault.get().getUser().getLevel());
                                        user.setId(dr.getKey());
                                        user.setRegistrado("false");
                                        dr.setValue(user);
                                    }else{
                                        Toast.makeText(Exchange.this, "Ya le has enviado una petición", Toast.LENGTH_SHORT).show();
                                    }
                                }else{
                                    DatabaseReference dr = FirebaseDatabase.getInstance().getReference("Usuario")
                                            .child(u.getId())
                                            .child("amigos").push();


                                    Usuario user = new Usuario();
                                    user.setFotoperfil(ValoresDefault.get().getUser().getFotoperfil());
                                    user.setCoins(ValoresDefault.get().getUser().getCoins());
                                    user.setEmail(ValoresDefault.get().getUser().getEmail());
                                    user.setUserName(ValoresDefault.get().getUser().getUserName());
                                    user.setLevel(ValoresDefault.get().getUser().getLevel());
                                    user.setId(dr.getKey());
                                    user.setRegistrado("false");
                                    dr.setValue(user);
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                    }

                } else {
                    Toast.makeText(Exchange.this, "No existe ningún usuario con ese username.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void obtenerPeticionesAmigos() {
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
        Query query = myRef.child("Usuario")
                .child(ValoresDefault.get().getUser().getId())
                .child("amigos")
                .orderByChild("registrado")
                .equalTo("false");

        Log.i("Memes",ValoresDefault.get().getUser().getId());
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    ArrayList<Usuario> userPeticion = new ArrayList<>();
                    for (DataSnapshot peticion : snapshot.getChildren()) {
                        Log.i("Memes",peticion.getValue(Usuario.class).getId());
                        userPeticion.add(peticion.getValue(Usuario.class));
                    }
                    Log.i("Memes","hola");

                    TextView tw = (TextView) findViewById(R.id.peticionesamistad);

                    tw.setAlpha(1);
                    tw.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            AlertDialog.Builder alerta = new AlertDialog.Builder(Exchange.this);

                            RelativeLayout rl = new RelativeLayout(Exchange.this);
                            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                            rl.setLayoutParams(params);
                            rl.setGravity(Gravity.CENTER);
                            rl.setPadding(0, 12, 0, 0);


                            AlertDialog title = alerta.create();
                            title.setTitle("Peticiones de amistad");

                            for (Usuario u : userPeticion) {
                                LayoutInflater lf = LayoutInflater.from(Exchange.this);
                                View v = lf.inflate(R.layout.plantilla_peticion_amistad, null);

                                TextView tv = v.findViewById(R.id.nombre);
                                ImageButton ib = v.findViewById(R.id.botonComprar);
                                ib.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                        Log.i("Memes",u.getId());

                                        FirebaseDatabase.getInstance().getReference()
                                                .child("Usuario")
                                                .child(ValoresDefault.get().getUser().getId())
                                                .child("amigos")
                                                .child(u.getId())
                                                .child("registrado").setValue("true");

                                        DatabaseReference dr = FirebaseDatabase.getInstance().getReference();
                                        Query q= dr.child("Usuario")
                                                .orderByChild("userName")
                                                .equalTo(u.getUserName());

                                        q.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                if(snapshot.exists()){
                                                    for(DataSnapshot ds : snapshot.getChildren()){

                                                        DatabaseReference data = FirebaseDatabase.getInstance().getReference("Usuario")
                                                                .child(ds.getValue(Usuario.class).getId())
                                                                .child("amigos")
                                                                .push();

                                                        Usuario user = new Usuario();
                                                        user.setFotoperfil(ValoresDefault.get().getUser().getFotoperfil());
                                                        user.setCoins(ValoresDefault.get().getUser().getCoins());
                                                        user.setEmail(ValoresDefault.get().getUser().getEmail());
                                                        user.setUserName(ValoresDefault.get().getUser().getUserName());
                                                        user.setLevel(ValoresDefault.get().getUser().getLevel());
                                                        user.setId(data.getKey());
                                                        user.setRegistrado("true");

                                                        data.setValue(user);

                                                    }
                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {

                                            }
                                        });
                                        tw.setVisibility(View.GONE);
                                        title.cancel();
                                        obtenerAmigos();
                                    }
                                });
                                v.findViewById(R.id.botonVender).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        FirebaseDatabase.getInstance().getReference()
                                                .child("Usuario")
                                                .child(ValoresDefault.get().getUser().getId())
                                                .child("amigos")
                                                .child(u.getId()).removeValue();
                                        title.cancel();
                                    }
                                });
                                //todo cancelar
                                tv.setText(u.getUserName());
                                rl.addView(v, params);

                            }
                            title.setView(rl);
                            title.show();
                            TextView peticiones = (TextView) findViewById(R.id.peticionesamistad);
                            peticiones.setAlpha(1);
                            obtenerAmigos();
                        }
                    });
                    if (userPeticion.isEmpty()) {
                        tw.setVisibility(View.GONE);
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void obtenerAmigos() {


        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
        Query query = myRef.child("Usuario")
                .child(ValoresDefault.get().getUser().getId())
                .child("amigos")
                .orderByChild("registrado")
                .equalTo("true");

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot user : snapshot.getChildren()){
                        Usuario u=user.getValue(Usuario.class);

                        LayoutInflater lf = LayoutInflater.from(Exchange.this);
                        View v = lf.inflate(R.layout.plantillalarga, null);

                        Button b1 = v.findViewById(R.id.botonComprar);
                        b1.setVisibility(View.GONE);
                        Button b2 = v.findViewById(R.id.botonVender);
                        b2.setVisibility(View.GONE);

/*
                        ImageView perfilimg = (ImageView) findViewById(R.id.imagenPerfil);
                        Picasso.get()
                                .load("https://firebasestorage.googleapis.com/v0/b/memedex-aa951.appspot.com/o/perfil1.JPG?alt=media&token=83ea862d-0d65-4407-be65-acb0b68d97bf")
                                .into(perfilimg);

                        int laFoto= u.getFotoperfil();
                        if (laFoto==0){
                            Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/memedex-aa951.appspot.com/o/perfil1.JPG?alt=media&token=83ea862d-0d65-4407-be65-acb0b68d97bf").into(perfilimg);
                        } else if (laFoto==1){
                            Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/memedex-aa951.appspot.com/o/perfil3.jpg?alt=media&token=c68b1b32-6cd9-4fa8-9115-92531611d1d9").into(perfilimg);
                        } else if (laFoto==2){
                            Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/memedex-aa951.appspot.com/o/perfil2.png?alt=media&token=1151d2b6-23e6-4386-a9a0-c21e2679bdff").into(perfilimg);
                        } else if (laFoto==3){
                            Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/memedex-aa951.appspot.com/o/perfil1.JPG?alt=media&token=83ea862d-0d65-4407-be65-acb0b68d97bf").into(perfilimg);
                        }
*/
                        TextView tv = v.findViewById(R.id.nombreMeme);
                        tv.setText(u.getUserName());

                        GridLayout rl = (GridLayout) findViewById(R.id.amigos);
                        rl.addView(v);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
