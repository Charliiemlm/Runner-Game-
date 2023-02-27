package com.AronAlvaroCarlos.runnergame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;

public class Preferencias extends AppCompatActivity {
    private Spinner spinner_array;
    private String valorSeleccionado;
     RadioButton uno;
     RadioButton dos;
     RadioButton tres;
    private int disparos_max;
    public int nivel;
     Button guardar;
     Button volver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        nivel=0;
      setContentView(R.layout.activity_preferencias);
      spinner_array= findViewById(R.id.spinner);
        uno = findViewById(R.id.uno);
        dos = findViewById(R.id.dos);
        tres = findViewById(R.id.tres);
        valorSeleccionado = spinner_array.getSelectedItem().toString();
        guardar= findViewById(R.id.guardar);
        disparos_max=5;
        volver=findViewById(R.id.atrás);
        uno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                disparos_max=1;
                valorSeleccionado = spinner_array.getSelectedItem().toString();
            }
        });
        dos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                disparos_max=3;
                valorSeleccionado = spinner_array.getSelectedItem().toString();

            }
        });
        tres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                disparos_max=5;
                valorSeleccionado = spinner_array.getSelectedItem().toString();
            }
        });
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valorSeleccionado = spinner_array.getSelectedItem().toString();
                switch (valorSeleccionado){
                    case "Easy":
                        nivel=0;
                        break;
                    case "Medium":
                        nivel=20;
                        break;
                    case "Hell":
                        nivel=200;
                        break;

                }
            finish();
            }
        });

        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


}