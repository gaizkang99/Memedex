package com.example.memedex.pantallas.registro;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.memedex.modelo.Usuario;
import com.example.memedex.pantallas.menu.Menu;
import com.example.memedex.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

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
                boolean activo = terminos.isChecked();
                if(activo && checkMail() && checkUser() && checkPassword()){
                    crearUsuario();
                    Intent intentSign = new Intent(SignIn.this, Menu.class);
                    startActivity(intentSign);
                }
                if(!activo){
                    Toast.makeText(getApplicationContext(), "Acepta los terminos y condiciones", Toast.LENGTH_SHORT).show();
                }
                if(!checkMail()){
                    Toast.makeText(getApplicationContext(), "El correo introducido es incorrecto", Toast.LENGTH_SHORT).show();
                }
                if(!checkUser()){
                    Toast.makeText(getApplicationContext(), "El usuario introducido ya existe", Toast.LENGTH_SHORT).show();
                }
                if(!checkPassword()){
                    Toast.makeText(getApplicationContext(), "La contraseña introducida es incorrecta", Toast.LENGTH_SHORT).show();
                }

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

    public boolean checkMail(){
        String correo = email.getText().toString();
        int arroba = 0;
        int punto = 0;
        boolean acertado = false;
        for(int i=0; i<correo.length(); i++){
            if(correo.charAt(i) == '@'){
                arroba++;
            }
            if(correo.charAt(i) == '.'){
                punto++;
            }
        }
        if(arroba==1 && punto>0){
            acertado=true;
        }
        return acertado;
    }

    public boolean checkUser(){
        boolean acertado = true;
        return acertado;
    }

    public boolean checkPassword(){
        boolean acertado = false;
        if(password.length()>6){
            acertado = true;
        }
        return acertado;
    }
}
