package com.AronAlvaroCarlos.runnergame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button bPuntuaciones;
    private Button bPreferencias;
    private Button bJuego;

    private Button bSalir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    Typeface spaceFont = Typeface.createFromAsset(getAssets(),
            "spaceage.ttf");
    TextView tituloApp = (TextView) findViewById(R.id.titulo);
		tituloApp.setTypeface(spaceFont);

    bJuego = (Button)findViewById(R.id.button1);

		bJuego.setOnClickListener(new OnClickListener() {

        @Override
        public void onClick(View v) {
            lanzarJuego(null);

        }
    });


    bPuntuaciones = (Button) findViewById(R.id.button3);
		bPuntuaciones.setOnClickListener(new OnClickListener() {

        @Override
        public void onClick(View v) {
            lanzarPuntuaciones(null);

        }
    });

    bPreferencias = (Button) findViewById(R.id.button2);
		bPreferencias.setOnClickListener(new OnClickListener() {

        @Override
        public void onClick(View v) {
            lanzarPreferencias(null);
        }
    });
    bSalir = (Button) findViewById(R.id.button4);
		bPreferencias.setOnClickListener(new OnClickListener() {

        @Override
        public void onClick(View v) {
            salir();
        }
    });
}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        switch (item.getItemId()) {
            case R.id.acercaDe:
                lanzarAcercaDe(null);
                break;
            case R.id.config:
                lanzarPreferencias(null);
                break;
        }
        return true;
    }


    public void lanzarPreferencias(View view) {
        Intent i = new Intent(this, Preferencias.class);
        startActivity(i);
    }

    public void lanzarPuntuaciones(View view) {
        Intent i = new Intent(this, Puntuaciones.class);
        startActivity(i);
    }
    public void lanzarJuego(View view) {
        Intent i = new	Intent(this, Juego.class);
        startActivity(i);
    }

    public void salir() {
        this.finish();
    }

}