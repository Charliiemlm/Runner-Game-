package com.AronAlvaroCarlos.runnergame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class Juego extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        VistaJuego puntos =new VistaJuego(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);
    }

}