package com.example.memedex.pantallas.menu.capturar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.memedex.R;
import com.example.memedex.pantallas.registro.Login;
import com.example.memedex.pantallas.registro.MainActivity;
import com.example.memedex.pantallas.registro.SignIn;

public class memeAtrapado  extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meme_atrapado);

        Button bottonVolverCaptura = (Button) findViewById(R.id.bottonVolverCaptura);
        Button bottonMenuPrincipal = (Button) findViewById(R.id.bottonMenuPrincipal);

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
}
