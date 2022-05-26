package com.example.memedex.pantallas.menu.perfil;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.memedex.R;
import com.squareup.picasso.Picasso;

public class LogroRegistro extends AppCompatActivity {

    private boolean bo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logro_registrado);
        TextView tv;

        String titulo = getIntent().getStringExtra("titulo");
        tv = (TextView) findViewById(R.id.nombreMemeCapturado);
        tv.setText(titulo);

        String desc = getIntent().getStringExtra("descripcion");
        tv = (TextView) findViewById(R.id.desc);
        tv.setText("Descripción: " + desc);

        String img = getIntent().getStringExtra("img");
        ImageView iv = (ImageView) findViewById(R.id.imageView1);
        Picasso.get().load(img).into(iv);
        bo = true;
        RelativeLayout rl = (RelativeLayout) findViewById(R.id.hijo21);
        RelativeLayout.LayoutParams w = (RelativeLayout.LayoutParams) rl.getLayoutParams();
        iv.setOnClickListener(view -> {
            if (bo) {
                DisplayMetrics metrics = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(metrics);
                int width = (int) (metrics.widthPixels); // ancho absoluto en pixels
                int height = (int) (metrics.heightPixels); // alto absoluto en pixels

                RelativeLayout.LayoutParams params =
                        new RelativeLayout.LayoutParams(
                                width,
                                height);
                params.addRule(RelativeLayout.BELOW, R.id.hijo1);
                rl.setLayoutParams(params);
            } else {
                rl.setLayoutParams(w);
            }
            bo = !bo;
        });

        Button b = (Button) findViewById(R.id.bottonMemedex);
        b.setOnClickListener(view -> {
            Intent intentSign = new Intent(LogroRegistro.this, Profile.class);
            startActivity(intentSign);
        });
    }
}
