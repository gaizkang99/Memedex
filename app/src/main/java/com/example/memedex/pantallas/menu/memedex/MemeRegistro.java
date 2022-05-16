package com.example.memedex.pantallas.menu.memedex;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.memedex.R;
import com.example.memedex.pantallas.menu.Menu;
import com.squareup.picasso.Picasso;

public class MemeRegistro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meme_registrado);
        TextView tv;
        /*Button button = (Button) findViewById(R.id.buttonMemedex);

        Button back = findViewById(R.id.buttonMemedex);
        back.setOnClickListener(view -> {
            Intent intentSign = new Intent(MemeRegistro.this, Menu.class);
            startActivity(intentSign);
        });*/
        String titulo = getIntent().getStringExtra("titulo");
        Log.i("Memes",titulo);
        tv= (TextView) findViewById(R.id.nombreMemeCapturado);
        tv.setText(titulo);

        String desc = getIntent().getStringExtra("descripcion");
        tv = (TextView) findViewById(R.id.desc);
        tv.setText(desc);

        String img = getIntent().getStringExtra("img");
        ImageView iv = (ImageView) findViewById(R.id.imageView1);
        Picasso.get().load(img).into(iv);
        Log.i("Memes",img);

    }
}
