package com.example.memedex.pantallas.registro;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.memedex.modelo.Usuario;
import com.example.memedex.modelo.ValoresDefault;
import com.example.memedex.pantallas.menu.Menu;
import com.example.memedex.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    private TextInputEditText userMail, password, confPassword, userName;
    private Button register;
    private TextView login;
    private Switch terminos;
    private FirebaseDatabase fb;
    private FirebaseAuth firebaseauth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        userName = findViewById(R.id.userName);
        userMail = findViewById(R.id.userMail);
        password = findViewById(R.id.passwd);
        confPassword = findViewById(R.id.confPasswd);
        terminos = findViewById(R.id.terminos);
        register = findViewById(R.id.register);
        login = findViewById(R.id.login);
        firebaseauth = FirebaseAuth.getInstance();

        Button back = (Button) findViewById(R.id.back);

        DatabaseReference ref= FirebaseDatabase.getInstance().getReference().child("Usuario");


        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Register.this, Login.class );
                startActivity(i);
            }
        });

        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String user = userMail.getText().toString();
                String pwd = password.getText().toString();
                String confpwd = confPassword.getText().toString();
                boolean activo = terminos.isChecked();

                if (!pwd.equals(confpwd)){
                    Toast.makeText(Register.this , "Check both password",Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(pwd) && TextUtils.isEmpty(confpwd) && TextUtils.isEmpty(pwd)){
                    Toast.makeText(Register.this , "Add credentials...",Toast.LENGTH_SHORT).show();
                } else if (!activo) {
                    Toast.makeText(getApplicationContext(), "Acepta los terminos y condiciones", Toast.LENGTH_SHORT).show();
                } else {
                    firebaseauth.createUserWithEmailAndPassword(user, pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(Register.this , "USER REGISTERED !!",Toast.LENGTH_SHORT).show();
                                crearUsuario(); //RealtimeDatabase
                                Intent i = new Intent(Register.this, Menu.class );
                                startActivity(i);
                                finish();
                            } else {
                                Toast.makeText(Register.this , "Fail register...",Toast.LENGTH_SHORT).show();

                            }
                        }
                    });

                }

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentLogin = new Intent(Register.this, MainActivity.class);
                startActivity(intentLogin);
            }
        });
    }
    public void crearUsuario(){
        fb = FirebaseDatabase.getInstance();
        DatabaseReference usuario = fb.getReference("Usuario");
        Usuario user = new Usuario(userName.getText().toString(), userMail.getText().toString());
        ValoresDefault.setUser(user);
        usuario.push().setValue(user);
        //update
        //usuario.child("uri").child("").setValue();
    }
}