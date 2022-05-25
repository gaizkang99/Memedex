package com.example.memedex.pantallas.menu.coleccion;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.memedex.R;
import com.example.memedex.modelo.Meme;
import com.example.memedex.pantallas.menu.capturar.Capturar;
import com.squareup.picasso.Picasso;

public class memeColeccion extends AppCompatActivity {
    boolean bo;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meme_atrapado);

        Meme meme = new Meme();
        meme.setNombre(getIntent().getExtras().getString("titulo"));
        meme.setImg(getIntent().getExtras().getString("imgurl"));
        meme.setImg(getIntent().getExtras().getString("tipo"));

        TextView tw = (TextView) findViewById(R.id.nombreMemeCapturado);
        tw.setText(meme.getNombre());
        ImageView iv = (ImageView) findViewById(R.id.imageView1);
        Picasso.get().load(meme.getImg()).into(iv);


        TextView tipo = (TextView) findViewById(R.id.tipo);
        tipo.setText(meme.getTipo());

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
            //iv.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;
            //iv.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
        });

        Button bottonVolverCaptura = (Button) findViewById(R.id.bottonVolverCaptura);
        Button bottonMenuPrincipal = (Button) findViewById(R.id.bottonMemedex);
        bottonVolverCaptura.setAlpha(0);
        bottonMenuPrincipal.setAlpha(0);

        bottonVolverCaptura.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(memeColeccion.this, Coleccion.class);
                startActivity(i);
            }
        });

        bottonMenuPrincipal.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent i = new Intent(memeColeccion.this, Coleccion.class);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == event.KEYCODE_BACK) {
            Intent i = new Intent(this, Coleccion.class);
            startActivity(i);
        }
        return super.onKeyDown(keyCode, event);
    }

}
