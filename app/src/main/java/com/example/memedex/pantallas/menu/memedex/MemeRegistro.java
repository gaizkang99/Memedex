package com.example.memedex.pantallas.menu.memedex;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.memedex.R;
import com.example.memedex.modelo.Meme;
import com.example.memedex.pantallas.menu.Menu;
import com.squareup.picasso.Picasso;

public class MemeRegistro extends AppCompatActivity {

    private boolean bo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meme_registrado);
        TextView tv;

        String titulo = getIntent().getStringExtra("titulo");
        Log.i("Memes",titulo);
        tv= (TextView) findViewById(R.id.nombreMemeCapturado);
        tv.setText(titulo);

        String desc = getIntent().getStringExtra("descripcion");
        tv = (TextView) findViewById(R.id.desc);
        tv.setText("Descripción: "+ desc);

        String img = getIntent().getStringExtra("img");
        ImageView iv = (ImageView) findViewById(R.id.imageView1);
        Picasso.get().load(img).into(iv);
        Log.i("Memes",img);
        bo=true;
        RelativeLayout rl = (RelativeLayout) findViewById(R.id.hijo21);
        RelativeLayout.LayoutParams w= (RelativeLayout.LayoutParams) rl.getLayoutParams();
        iv.setOnClickListener(view -> {
            if(bo){
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
            }else{
                rl.setLayoutParams(w);
            }
            bo=!bo;
            //iv.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;
            //iv.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
        });

        Button b = (Button) findViewById(R.id.bottonMemedex);
        b.setOnClickListener(view -> {
            Intent intentSign = new Intent(MemeRegistro.this, Memedex.class);
            startActivity(intentSign);
        });
    }
}
