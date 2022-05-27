package com.example.memedex.pantallas.menu.perfil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.memedex.R;
import com.example.memedex.modelo.ValoresDefault;
import com.example.memedex.pantallas.menu.Menu;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class Exchange extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exchange);

        ImageButton back = (ImageButton) findViewById(R.id.back);
        Button addFriend = (Button) findViewById(R.id.addFriend);

        TextView peticiones = (TextView) findViewById(R.id.peticionesamistad);

        obtenerPeticionesAmigos();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSign = new Intent(Exchange.this, Menu.class);
                startActivity(intentSign);
            }
        });

        peticiones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listarPeticionesAmigos();
            }
        });
        addFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //sendPeticion();
            }
        });

    }


    private void sendPeticion() {
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
        Query query= myRef.child("Usuario")
                .child(ValoresDefault.get().getUser().getId())
                .child("amistades")
                .orderByChild("validado")
                .equalTo(false);

    }

    private void listarPeticionesAmigos() {

    }

    private void obtenerPeticionesAmigos() {
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
        Query query= myRef.child("Usuario")
                .child(ValoresDefault.get().getUser().getId())
                .child("amistades")
                .orderByChild("validado")
                .equalTo(false);
    }
    private void obtenerAmigos() {

        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
        Query query= myRef.child("Usuario")
                .child(ValoresDefault.get().getUser().getId())
                .child("amistades")
                .orderByChild("validado")
                .equalTo(true);
    }

}
