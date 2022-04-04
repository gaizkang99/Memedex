package com.example.memedex;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Memedex extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.memedex);

        Button back = findViewById(R.id.back);


        back.setOnClickListener(view -> {
            Intent intentSign = new Intent(Memedex.this, Menu.class);
            startActivity(intentSign);
        });

    }
}
