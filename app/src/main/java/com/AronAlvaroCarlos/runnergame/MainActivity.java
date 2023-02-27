package com.AronAlvaroCarlos.runnergame;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  {

    private MediaPlayer musica_fondo;

    private Button bPreferencias;
    private Button bJuego;

    private Button bSalir;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    bJuego= (Button) findViewById(R.id.bt_jugar);

    bPreferencias= (Button) findViewById(R.id.bt_preferencias);

    bSalir= findViewById(R.id.bt_salir);
    musica_fondo=MediaPlayer.create(getApplicationContext(),R.raw.cottagecore);
    musica_fondo.start();


        bJuego.setOnClickListener(new View.OnClickListener() {
            @Override
        public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Juego.class);
                startActivity(i);
                musica_fondo.stop();
        }
    });

		bPreferencias.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            Intent j=new Intent(MainActivity.this, Preferencias.class);
            startActivity(j);
        }
    });
        bSalir.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            salir();
        }
    });
}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public void salir() {
        musica_fondo.stop();
        this.finish();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // La pantalla está en modo paisaje
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            // La pantalla está en modo retrato
        }
    }



}