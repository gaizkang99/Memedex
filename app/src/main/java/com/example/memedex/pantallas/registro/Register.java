package com.example.memedex.pantallas.registro;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Locale;

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

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Usuario");


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Register.this, Login.class);
                startActivity(i);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(userMail.getText().toString().equals(" ")||userMail.getText().toString().equals("")){
                    Toast.makeText(Register.this, "Inserta un correo valido ", Toast.LENGTH_SHORT).show();
                }
                //Verifica que se inserte un usuario
                else if(userName.getText().toString().contains(" ")||userName.getText().toString().equals("")){
                    Toast.makeText(Register.this, "Inserta un usuario sin espacios", Toast.LENGTH_SHORT).show();
                }
                //Verificación de contraseña
                else if (!password.getText().toString().equals(confPassword.getText().toString())) {
                    Toast.makeText(Register.this, "Comprueba la contraseña", Toast.LENGTH_SHORT).show();
                }
                //Verificar si falta algún texto
                else if (TextUtils.isEmpty(password.getText().toString()) && TextUtils.isEmpty(confPassword.getText().toString())) {
                    Toast.makeText(Register.this, "Inserta las contraseñas", Toast.LENGTH_SHORT).show();
                }
                //Verifica si as aceptado los terminos
                else if (!terminos.isChecked()) {
                    Toast.makeText(getApplicationContext(), "Acepta los terminos y condiciones", Toast.LENGTH_SHORT).show();
                }else{
                    DatabaseReference verificarUsername = FirebaseDatabase.getInstance().getReference();
                    Query query = verificarUsername.child("Usuario")
                            .orderByChild("userName")
                            .equalTo(userName.getText().toString().toLowerCase(Locale.ROOT));
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            //Si existe el NO username registra
                            if(!snapshot.exists()){
                                //Crea el usuario
                                firebaseauth.createUserWithEmailAndPassword(userMail.getText().toString().toLowerCase(Locale.ROOT), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(Register.this, "USER REGISTERED !!", Toast.LENGTH_SHORT).show();
                                            crearUsuario(); //RealtimeDatabase
                                            Intent i = new Intent(Register.this, Menu.class);
                                            startActivity(i);
                                            finish();
                                        } else {
                                            Toast.makeText(Register.this, "Error de conexión", Toast.LENGTH_SHORT).show();

                                        }
                                    }
                                });

                            }else{
                                Toast.makeText(getApplicationContext(), "Este usuario ya existe", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

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

    public void crearUsuario() {
        fb = FirebaseDatabase.getInstance();
        DatabaseReference usuario = fb.getReference("Usuario");
        Usuario user = new Usuario(
                usuario.push().getKey(),
                userName.getText().toString(),
                userMail.getText().toString(),
                300,
                1);

        ValoresDefault.get().setUser(user);
        usuario.child(user.getId()).setValue(user);

    }
}