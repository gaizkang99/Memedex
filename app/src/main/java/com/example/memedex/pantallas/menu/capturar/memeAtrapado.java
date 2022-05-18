package com.example.memedex.pantallas.menu.capturar;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.memedex.R;
import com.example.memedex.modelo.Meme;
import com.example.memedex.pantallas.menu.Menu;
import com.squareup.picasso.Picasso;

public class memeAtrapado  extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meme_atrapado);

        Meme meme= new Meme();
        meme.setNombre(getIntent().getExtras().getString("name"));
        meme.setImg(getIntent().getExtras().getString("imgurl"));

        TextView tw = (TextView) findViewById(R.id.nombreMemeCapturado);
        tw.setText(meme.getNombre());
        ImageView iv = (ImageView) findViewById(R.id.imageView1);
        Picasso.get().load(meme.getImg()).into(iv);



        Button bottonVolverCaptura = (Button) findViewById(R.id.bottonVolverCaptura);
        Button bottonMenuPrincipal = (Button) findViewById(R.id.bottonMemedex);

        bottonVolverCaptura.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intentSign = new Intent(memeAtrapado.this, Capturar.class);
                startActivity(intentSign);
            }
        });

        bottonMenuPrincipal.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intentLogin = new Intent(memeAtrapado.this, Menu.class);
                startActivity(intentLogin);
            }
        });
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==event.KEYCODE_BACK){
            Intent i = new Intent(this, Capturar.class);
            startActivity(i);
        }
        return super.onKeyDown(keyCode, event);
    }

}
