package com.example.memedex.pantallas.registro;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.memedex.modelo.ValoresDefault;
import com.example.memedex.pantallas.menu.Menu;
import com.example.memedex.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity {

    private TextInputEditText username, password;
    private Button login;
    private TextView register;
    private FirebaseDatabase fb;
    private FirebaseAuth firebaseauth;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.passwd);
        login = findViewById(R.id.logIn);
        register = findViewById(R.id.register);
        firebaseauth = FirebaseAuth.getInstance();

        Button back = (Button) findViewById(R.id.back);

        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login.this, Register.class );
                startActivity(i);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pwd = password.getText().toString();
                /*if (TextUtils.isEmpty(user) && TextUtils.isEmpty(pwd)){
                    Toast.makeText(Login.this , "Enter credentials...",Toast.LENGTH_SHORT).show();
                } else {
                    firebaseauth.signInWithEmailAndPassword(user, pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                //Recoger usuario por correo
                                setValoresDefault();
                                Toast.makeText(Login.this , "Login succesfull !!",Toast.LENGTH_SHORT).show();*/
                                Intent i = new Intent(Login.this, Menu.class );
                                startActivity(i);/*
                                finish();
                            } else {
                                Toast.makeText(Login.this , "Failed login !!",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }*/
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLogin = new Intent(Login.this, MainActivity.class);
                startActivity(intentLogin);
            }
        });
    }

    private void setValoresDefault(){

    }
}