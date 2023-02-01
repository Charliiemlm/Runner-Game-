package com.AronAlvaroCarlos.runnergame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private Button bPuntuaciones;
    private Button bPreferencias;
    private Button bJuego;

    private Button bSalir;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
    Typeface spaceFont = Typeface.createFromAsset(getAssets(),
            "spaceage.ttf");
    TextView tituloApp = (TextView) findViewById(R.id.titulo);
		tituloApp.setTypeface(spaceFont);
*/
    bJuego= findViewById(R.id.bt_jugar);

    bPreferencias= findViewById(R.id.bt_preferencias);

    bPuntuaciones= findViewById(R.id.bt_puntuaciones);
    bSalir= findViewById(R.id.bt_salir);


        bJuego.setOnClickListener(new View.OnClickListener() {
            @Override
        public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Juego.class);
                startActivity(i);
        }
    });


		bPuntuaciones.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            //lanzarPuntuaciones(null);

        }
    });

		bPreferencias.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            lanzarPreferencias(null);
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




    public void lanzarPreferencias(View view) {
       // Intent i = new Intent(this, Preferencias.class);
       // startActivity(i);
    }

    public void lanzarPuntuaciones(View view) {
      //  Intent i = new Intent(this, Puntuaciones.class);
       // startActivity(i);
    }
    public void lanzarJuego(View view) {
        Intent i = new	Intent(this, Juego.class);
        startActivity(i);
    }

    public void salir() {
        this.finish();
    }

}