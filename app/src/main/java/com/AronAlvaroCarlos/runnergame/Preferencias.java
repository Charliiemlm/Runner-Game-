package com.AronAlvaroCarlos.runnergame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class Preferencias extends AppCompatActivity {
    private TextView Dificultad;
    private Spinner spinner_array;
    public Bundle bundle;
    private String valorSeleccionado;
    private TextView disparos;
    private RadioButton uno;
    private RadioButton dos;
    private RadioButton tres;
    private int disparos_max;
    public int nivel;
    private Button guardar;
    private Button volver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        nivel=0;
      setContentView(R.layout.activity_preferencias);
      Dificultad=(TextView) findViewById(R.id.dificultad);
      spinner_array=(Spinner) findViewById(R.id.spinner);
      disparos=(TextView) findViewById(R.id.disparos);
        uno = (RadioButton) findViewById(R.id.uno);
        dos = (RadioButton) findViewById(R.id.dos);
        tres = (RadioButton) findViewById(R.id.tres);
        valorSeleccionado = spinner_array.getSelectedItem().toString();
        guardar=(Button) findViewById(R.id.guardar);
        disparos_max=5;
        volver=(Button)findViewById(R.id.atrás);
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
                System.out.println(disparos_max);
                VistaJuego vistaJuego=new VistaJuego(Preferencias.this);
                vistaJuego.setDisparosRestantes(disparos_max);
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
   public void getDisparos_max(int nivel){
    }

}