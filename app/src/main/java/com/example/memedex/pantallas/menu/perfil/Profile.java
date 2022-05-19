package com.example.memedex.pantallas.menu.perfil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.memedex.R;
import com.example.memedex.modelo.Logro;
import com.example.memedex.modelo.Meme;
import com.example.memedex.pantallas.menu.Menu;

import java.util.ArrayList;

public class Profile extends AppCompatActivity {
    private ArrayList<Logro> logros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        logros = new ArrayList<>();
        Button back = (Button) findViewById(R.id.back);
        Button friends = (Button) findViewById(R.id.friends);

        getMisLogros();
        
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
                Intent intentSign = new Intent(Profile.this, Friends.class);
                startActivity(intentSign);
            }
        });


    }

    private void getMisLogros() {

    }

}
