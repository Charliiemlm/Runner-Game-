package com.AronAlvaroCarlos.runnergame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.Vector;

public class VistaJuego extends View {

    private Grafico personaje, cactus,avion; // Gráficos


    /*
    private int giroNave; // Incremento de dirección
    private float aceleracionNave; // aumento de velocidad
    // Incremento estándar de giro y aceleración
    private static final int PASO_GIRO_NAVE = 5;
    private static final float PASO_ACELERACION_NAVE = 0.5f;
    */
    //hola

    public VistaJuego(Context context, AttributeSet attrs) {

        super(context, attrs);

        Drawable drawablePersonaje, drawableCactus, drawableAvion;


        //Instanciando Personaje
        drawablePersonaje = context.getResources().getDrawable(R.drawable.personaje);
        personaje = new Grafico(this, drawablePersonaje);


       //Instanciando los cactus
        drawableCactus = context.getResources().getDrawable(
                R.drawable.cactus);
         cactus = new Grafico(this, drawableCactus);
        cactus.setIncY(Math.random() * 4 - 2);
        cactus.setIncX(Math.random() * 4 - 2);

        //Instanciando el avion
        drawableAvion = context.getResources().getDrawable(
                R.drawable.avion);
         avion = new Grafico(this, drawableAvion);
        cactus.setIncY(Math.random() * 4 - 2);
        cactus.setIncX(Math.random() * 4 - 2);

    }

    @Override
    protected void onSizeChanged(int ancho, int alto, int ancho_anter,
                                 int alto_anter) {

        super.onSizeChanged(ancho, alto, ancho_anter, alto_anter);

        //posicionamos el personaje en el centro de la pantalla
        personaje.setPosX((ancho - personaje.getAncho()) /2);
        personaje.setPosY((alto - personaje.getAlto()) /2);

        //posicionamos el cactus en  pantalla
        cactus.setPosX((ancho - cactus.getAncho()) /4);
        cactus.setPosY((alto - cactus.getAlto()) /4);
        //posicionamos el avion en  pantalla
        avion.setPosX((ancho - avion.getAncho()) /4);
        avion.setPosY((alto - avion.getAlto()) /4);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        personaje.dibujaGrafico(canvas);
        avion.dibujaGrafico(canvas);
        cactus.dibujaGrafico(canvas);

    }
}
