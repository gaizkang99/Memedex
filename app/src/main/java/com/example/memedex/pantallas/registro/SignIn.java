package com.example.memedex.pantallas.registro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.example.memedex.modelo.Usuario;
import com.example.memedex.pantallas.menu.Menu;
import com.example.memedex.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignIn extends AppCompatActivity {

    private EditText email, username, password;
    private Button signIn, back;
    private Switch terminos;
    private FirebaseDatabase fb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);

        signIn = findViewById(R.id.signIn);
        back = findViewById(R.id.back);
        email = findViewById(R.id.email);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        terminos = findViewById(R.id.terminos);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crearUsuario();
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
        fb = FirebaseDatabase.getInstance();
        DatabaseReference usuario = fb.getReference("Usuario");
        Usuario user = new Usuario(username.getText().toString(), password.getText().toString(), email.getText().toString());
        usuario.push().setValue(user);
    }
}
