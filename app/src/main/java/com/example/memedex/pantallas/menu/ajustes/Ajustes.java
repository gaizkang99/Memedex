package com.example.memedex.pantallas.menu.ajustes;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.memedex.R;
import com.example.memedex.modelo.ValoresDefault;
import com.example.memedex.pantallas.menu.Menu;
import com.example.memedex.pantallas.registro.MainActivity;

public class Ajustes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajustes);

        Button atras = (Button) findViewById(R.id.atras);
        Button close = (Button) findViewById(R.id.cerrarSesion);


        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSign = new Intent(Ajustes.this, Menu.class);
                startActivity(intentSign);
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentSign = new Intent(Ajustes.this, MainActivity.class);
                startActivity(intentSign);
            }
        });
        //musica sonido
        SeekBar scrubSeekBar = (SeekBar) findViewById(R.id.musicaSeekbar);
        scrubSeekBar.setMax(100);

        scrubSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Log.i("Memes", "Musica "+Integer.toString(i));
                ValoresDefault.get().setMusica(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        SeekBar s = (SeekBar) findViewById(R.id.sonidoSeekbar);
        s.setMax(100);
        scrubSeekBar.setProgress((int) ValoresDefault.get().getMusica());

        s.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Log.i("Memes", "Sonido "+Integer.toString(i));
                ValoresDefault.get().setSonido(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }
}
