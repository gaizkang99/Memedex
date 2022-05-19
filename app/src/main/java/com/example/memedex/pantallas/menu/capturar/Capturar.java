package com.example.memedex.pantallas.menu.capturar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
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
import java.util.Random;

public class Capturar  extends AppCompatActivity {

    private final Random rand = new Random(); //rand.nextInt();
    private ArrayList<Meme> memes;
    private int contador=0;
    private CountDownTimer c;
    private MediaPlayer sonido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.capturar);

        sonido = MediaPlayer.create(this, R.raw.duck_sound);
        sonido.setVolume(ValoresDefault.get().getSonido(), ValoresDefault.get().getSonido());

        Button back = (Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c.cancel();
                Intent menu = new Intent(Capturar.this, Menu.class);
                startActivity(menu);
            }
        });


            c= new CountDownTimer(rand.nextInt(10) * 0, 1000) {
                public void onTick(long millisUntilFinished) {
                    // Used for formatting digit to be in 2 digits only
                    long hour = (millisUntilFinished / 3600000) % 24;
                    long min = (millisUntilFinished / 60000) % 60;
                    long sec = (millisUntilFinished / 1000) % 60;
                    Log.i("Memes", hour + ":" + min + ":" + sec);
                }

                // When the task is over it will print 00:00:00 there
                public void onFinish() {
                    Log.i("Memes", "00:00:00");
                    obtenerListadoMemes();

                }
            }.start();


    }

    private void obtenerListadoMemes() {


        memes = new ArrayList<Meme>();

        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();

        Query query = myRef.child("Meme");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot meme : dataSnapshot.getChildren()) {
                        memes.add(meme.getValue(Meme.class));
                    }
                }else
                    Toast.makeText(getApplicationContext(), "Error de conexión", Toast.LENGTH_LONG).show();

                imgConfigure(memes);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Error de conexión", Toast.LENGTH_LONG).show();

            }
        });

    }
    private void imgConfigure(ArrayList<Meme> memes){
        //Log.i("Memes",cronometro.getText().toString());
        final ImageView image = (ImageView) findViewById(R.id.memeCapturar);
        Meme re= memes.get(rand.nextInt(memes.size()-1));
        LoadImageFromWeb(re.getImg(),image,re);

    }
    private void LoadImageFromWeb(String url,ImageView imageView,Meme meme) {
        try {
            Picasso.get().load(url).into(imageView);

            miniJuegoMoverPulsarEsconder(imageView);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    contador++;
                    if(contador<3) {
                        sonido.start();
                        miniJuegoMoverPulsarEsconder(imageView);

                    }else {
                        //Update de tu colección
                        addToFirebaseUser(meme);

                        Intent intent = new Intent(Capturar.this, memeAtrapado.class);
                        intent.putExtra("name", meme.getTitulo());
                        intent.putExtra("imgurl", meme.getImg());
                        //intent.putExtra("tipo", );
                        startActivity(intent);
                    }
                }
            });

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Error de conexión", Toast.LENGTH_LONG).show();
        }
    }

    private void addToFirebaseUser(Meme meme) {

        FirebaseDatabase.getInstance().getReference()
                .child("Usuario")
                .child(ValoresDefault.get().getUser().getId())
                .child("coleccionMemes").push().setValue(meme);


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

        /*DatabaseReference memedex = FirebaseDatabase.getInstance().getReference();
        Query q=  memedex
                .child("Usuario")
                .child(ValoresDefault.get().getUser().getId())
                .child("memedexMemes").orderByChild("titulo").equalTo(meme.getTitulo());
        q.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int i = 0;
                if (snapshot.exists()) {
                    for (DataSnapshot m : snapshot.getChildren()) {
                        Log.i("Memes",m.getValue(Meme.class).getNombre());
                        FirebaseDatabase.getInstance().getReference()
                                .child("Usuario")
                                .child(ValoresDefault.get().getUser().getId())
                                .child("memedexMemes").setValue(meme);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/

        /*
        *
    private void addToFirebaseUser(Meme meme) {
        //Coleccion
        //Lo inserta a la colección
        DatabaseReference coleccion = FirebaseDatabase.getInstance()
                .getReference()
                .child("Usuario")
                .child(ValoresDefault.get().getUser().getId())
                .child("coleccionMemes");
        coleccion.setValue(meme);
        //Memedex
        //Verifica que no se ha registrado ningún meme que sea él mismo
        DatabaseReference memedex = FirebaseDatabase.getInstance().getReference();
        Query query= memedex
                .child("Usuario")
                .child(ValoresDefault.get().getUser().getId())
                .child("memedexMemes");
        memedex.setValue(meme);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    boolean yaCapturado=false;
                    for (DataSnapshot memeColeccion : dataSnapshot.getChildren()) {
                        if(meme==memeColeccion.getValue(Meme.class)){
                            yaCapturado=true;
                        }
                    }
                    if(yaCapturado){

                    }else{
                        memedex.push();
                        Intent intent = new Intent(Capturar.this, memeAtrapado.class);
                        intent.putExtra("name", meme.getTitulo());
                        intent.putExtra("imgurl", meme.getImg());
                        //intent.putExtra("tipo", );
                        startActivity(intent);
                    }
                }else
                    Toast.makeText(getApplicationContext(), "Error de conexión", Toast.LENGTH_LONG).show();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }*/

    }

    private void miniJuegoMoverPulsarEsconder(ImageView iv){
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = (int) (metrics.widthPixels/1.50); // ancho absoluto en pixels
        int height = (int) (metrics.heightPixels/1.40); // alto absoluto en pixels
        //Esconde imagen
        iv.animate()
                .alpha(0.0f)
                .setDuration(500);
        //Mueve la imagen, la hace visible

        iv.animate()
                .translationX(rand.nextInt(width))
                .translationY(rand.nextInt(height))
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        iv.animate().alpha(1.0f).setDuration(3000);
                    }
                })
        ;
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==event.KEYCODE_BACK){
            Intent i = new Intent(this, Menu.class);
            startActivity(i);
        }
        return super.onKeyDown(keyCode, event);
    }

}
