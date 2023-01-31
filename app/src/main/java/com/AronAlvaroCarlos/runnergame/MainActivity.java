package com.AronAlvaroCarlos.runnergame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    //Creación de Boton Jugar
    private Button Jugar;

    //Creación de Boton Salir
    private Button Salir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Jugar=findViewById(R.id.jugar);
        Salir=findViewById(R.id.Salir);
        //Implementamos el método que permite abrir la nueva actividad si se abre
        Jugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.class,GameActivity.class);
                startActivity(i);
                //Terminamos el Main Activity
                finish();
            }
        });
        //Implementamos el método que permite salirnos de la aplicacion
        Salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Nos salimos de la aplicación
                finish();
            }
        });

        Button boton20;
    }


}