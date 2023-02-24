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
    private String valorSeleccionado;
    private TextView disparos;
    private RadioButton uno;
    private RadioButton dos;
    private RadioButton tres;
    private int disparos_max;
    private Button guardar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        uno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                disparos_max=1;
                System.out.println(disparos_max);
                valorSeleccionado = spinner_array.getSelectedItem().toString();
                System.out.println(valorSeleccionado);
            }
        });
        dos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                disparos_max=3;
                System.out.println(disparos_max);
                valorSeleccionado = spinner_array.getSelectedItem().toString();
                System.out.println(valorSeleccionado);

            }
        });
        tres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                disparos_max=5;
                System.out.println(disparos_max);
                valorSeleccionado = spinner_array.getSelectedItem().toString();
                System.out.println(valorSeleccionado);
            }
        });
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            getDisparos_max();
            }
        });

    }
    public Intent getDisparos_max(){
        Intent a =new Intent(Preferencias.this, Grafico.class);
        a.putExtra("disparos",disparos_max);
        return a;
    }

}