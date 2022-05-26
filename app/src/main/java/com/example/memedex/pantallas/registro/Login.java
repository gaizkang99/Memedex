package com.example.memedex.pantallas.registro;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.memedex.modelo.Logro;
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

import java.util.Locale;

public class Login extends AppCompatActivity {

    private TextInputEditText usermail, password;
    private Button login;
    private TextView register;
    private FirebaseDatabase fb;
    private FirebaseAuth firebaseauth;
    private AlertDialog.Builder popupbuilder;
    private AlertDialog popup;
    private Button ok;

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

                String user = usermail.getText().toString().toLowerCase(Locale.ROOT);
                String pwd = password.getText().toString().toLowerCase(Locale.ROOT);
                if (TextUtils.isEmpty(user) && TextUtils.isEmpty(pwd)){
                    Toast.makeText(Login.this , "Enter credentials...",Toast.LENGTH_SHORT).show();
                } else {
                    firebaseauth.signInWithEmailAndPassword(user, pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                //Recoger usuario por correo
                                registroUsuario();

                                //LOGRO 1 --> Iniciar session
                                logroObtenido1();
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

    private void registroUsuario(){
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
        Query query= myRef
                .child("Usuario")
                .orderByChild("email")
                .equalTo(usermail.getText().toString().toLowerCase(Locale.ROOT));

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot user : snapshot.getChildren()) {
                        Usuario u= new Usuario(
                                user.getValue(Usuario.class).getId(),
                                user.getValue(Usuario.class).getUserName(),
                                user.getValue(Usuario.class).getEmail(),
                                user.getValue(Usuario.class).getCoins(),
                                user.getValue(Usuario.class).getLevel(),
                                user.getValue(Usuario.class).getFotoperfil()
                                );
                        ValoresDefault.get().setUser(u);

                        Intent intent = new Intent(Login.this, Menu.class );
                        startActivity(intent);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Login.this , "Error de conexión",Toast.LENGTH_SHORT).show();

            }
        });
    }


    private void logroObtenido1() {
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
        Query query= myRef
                .child("Logro")
                .orderByChild("nombre")
                .equalTo("login");

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    Logro newlogro;
                    for (DataSnapshot logru : snapshot.getChildren()) {
                        newlogro=  logru.getValue(Logro.class);
                        insertLogro(newlogro);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
    }

    private void insertLogro(Logro logro) {
        //Update level
        FirebaseDatabase.getInstance().getReference("Usuario")
                .child(ValoresDefault.get().getUser().getId())
                .child("level").setValue((ValoresDefault.get().getUser().getLevel())+1);

        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
        Query query= myRef
                .child("Usuario")
                .child(ValoresDefault.get().getUser().getId())
                .child("logro").orderByChild("nombre").equalTo(logro.getNombre());

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot l : snapshot.getChildren()) {
                       /* FirebaseDatabase.getInstance().getReference("Usuario")
                                .child(ValoresDefault.get().getUser().getId())
                                .child("logro").push().setValue(logro);*/
                    }
                }else{
                    FirebaseDatabase.getInstance().getReference("Usuario")
                            .child(ValoresDefault.get().getUser().getId())
                            .child("logro").push().setValue(logro);
                    newpopup();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
    }
    public void newpopup(){
        popupbuilder = new AlertDialog.Builder(this);
        final View contactPopupView = getLayoutInflater().inflate(R.layout.popup,null);
        ok = (Button) contactPopupView.findViewById(R.id.buton);

        popupbuilder.setView(contactPopupView);
        popup = popupbuilder.create();
        popup.show();

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popup.dismiss();
            }
        });
    }
}
