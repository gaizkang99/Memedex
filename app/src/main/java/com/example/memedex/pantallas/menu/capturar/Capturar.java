package com.example.memedex.pantallas.menu.capturar;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.memedex.R;
import com.example.memedex.pantallas.menu.ajustes.Ajustes;
import com.example.memedex.pantallas.registro.MainActivity;
import com.example.memedex.pantallas.registro.SignIn;

import java.util.Random;

public class Capturar  extends AppCompatActivity {

    private final Random rand = new Random(); //rand.nextInt();
    private final Integer[] imgID=
            {R.drawable.dam, R.drawable.hectortostring,R.drawable.sistemasxd};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.capturar);

        imgConfigure();
    }

    int contador = 0;
    private void imgConfigure(){

        final ImageView iv = (ImageView) findViewById(R.id.memeCapturar);
        int re= imgID[rand.nextInt(imgID.length)];
        iv.setImageResource(re);

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contador++;
                if(contador<3) {
                    MoveImg(iv);
                }else {
                    Intent intent = new Intent(Capturar.this, memeAtrapado.class);
                    startActivity(intent);
                }
            }
        });
/*
        final RelativeLayout rl = (RelativeLayout) findViewById(R.id.campoDeCaptura);
        rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(contador<0)
                    //pop-up de lo has perdido
                    Log.i("Hola","Lo has perdido");
                else
                    contador--;
            }
        });*/
    }

    private void MoveImg(ImageView iv){
            //medir pantalla
            DisplayMetrics metrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(metrics);

            int width = (int) (metrics.widthPixels/1.50); // ancho absoluto en pixels
            int height = (int) (metrics.heightPixels/1.40); // alto absoluto en pixels

            Log.i("Hola",+width +" "+height);

            //SEttings de la imagen
            iv.setVisibility(View.INVISIBLE);
            int re= imgID[rand.nextInt(imgID.length)];
            iv.setImageResource(re);

            iv.animate().translationX(rand.nextInt(width));
            iv.animate().translationY(rand.nextInt(height));
            iv.setVisibility(View.VISIBLE);
            ///sonido cuando aparece

    }

}
