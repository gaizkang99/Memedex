package com.example.memedex.pantallas.registro;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.memedex.modelo.Meme;
import com.example.memedex.modelo.Usuario;
import com.example.memedex.modelo.ValoresDefault;
import com.example.memedex.pantallas.menu.Menu;
import com.example.memedex.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Locale;

public class Login extends AppCompatActivity {

    private TextInputEditText usermail, password;
    private Button login;
    private TextView register;
    private FirebaseDatabase fb;
    private FirebaseAuth firebaseauth;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        usermail = findViewById(R.id.username);
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
                String user = usermail.getText().toString();
                String pwd = password.getText().toString();
                if (TextUtils.isEmpty(user) && TextUtils.isEmpty(pwd)){
                    Toast.makeText(Login.this , "Enter credentials...",Toast.LENGTH_SHORT).show();
                } else {
                    firebaseauth.signInWithEmailAndPassword(user, pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                //Recoger usuario por correo
                                setValoresDefault();
                                Toast.makeText(Login.this , "Login succesfull !!",Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(Login.this, Menu.class );
                                startActivity(i);
                                finish();
                            } else {
                                Toast.makeText(Login.this , "Failed login !!",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
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

        usermail = findViewById(R.id.username);

        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
        Query query = myRef.child("Usuario");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot user : dataSnapshot.getChildren()) {
                        if (user.child("email").getValue().equals(usermail)){
                            Usuario usuari = new Usuario(user.child("id").getValue().toString(),user.child("userName").getValue().toString(),user.child("email").getValue().toString());
                            ValoresDefault.get().setUser(usuari);

                        }
                    }
                }else
                    Toast.makeText(getApplicationContext(), "Error de conexión", Toast.LENGTH_LONG).show();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}