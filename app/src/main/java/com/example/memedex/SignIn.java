package com.example.memedex;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

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
}
