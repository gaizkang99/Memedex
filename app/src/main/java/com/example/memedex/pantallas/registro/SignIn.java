package com.example.memedex.pantallas.registro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.memedex.pantallas.menu.Menu;
import com.example.memedex.R;

public class SignIn extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);


        Button signIn = (Button) findViewById(R.id.signIn);
        Button back = (Button) findViewById(R.id.back);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSign = new Intent(SignIn.this, Menu.class);
                startActivity(intentSign);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLogin = new Intent(SignIn.this, MainActivity.class);
                startActivity(intentLogin);
            }
        });
    }

    public void crearUsuario(){
        EditText email = findViewById(R.id.email);
        EditText name = findViewById(R.id.username);
        EditText pass = findViewById(R.id.passwd);
        String nombre = String.valueOf(name.getText());
        String password = String.valueOf(pass.getText());
        String mail = String.valueOf(email.getText());
        if(nombre!=null && password!=null && mail!=null){

        }
    }
}
