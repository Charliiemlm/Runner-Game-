package com.AronAlvaroCarlos.runnergame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import java.util.Vector;

public class VistaJuego {

    // //// Personaje //////

    private Grafico personaje;// Gráfico de la nave

    private int giroNave; // Incremento de dirección

    private float aceleracionNave; // aumento de velocidad

    // Incremento estándar de giro y aceleración

    private static final int PASO_GIRO_NAVE = 5;

    private static final float PASO_ACELERACION_NAVE = 0.5f;

    // //// ASTEROIDES //////

    private Vector<Grafico> obstaculos; // Vector con los Asteroides

    private int numObstaculos = 5; // Número inicial de asteroides


    public VistaJuego(Context context, AttributeSet attrs) {

        super(context, attrs);

        Drawable drawablePersonaje, drawableObstaculo, drawableMisil;

        drawablePersonaje = context.getResources().getDrawable(R.drawable.personajeEstatico);
        personaje = new Grafico(this, drawablePersonaje);


        //Instaciando los Asteroides
        drawableObstaculo = context.getResources().getDrawable(
                R.drawable.cactus);

        obstaculos = new Vector<Grafico>();

        for (int i = 0; i < numObstaculos; i++) {

            Grafico asteroide = new Grafico(this, drawableObstaculo);

            asteroide.setIncY(Math.random() * 4 - 2);

            asteroide.setIncX(Math.random() * 4 - 2);

            asteroide.setAngulo((int) (Math.random() * 360));

            asteroide.setRotacion((int) (Math.random() * 8 - 4));

            obstaculos.add(asteroide);

        }

    }

    @Override
    protected void onSizeChanged(int ancho, int alto, int ancho_anter,
                                 int alto_anter) {

        super.onSizeChanged(ancho, alto, ancho_anter, alto_anter);

        // Una vez que conocemos nuestro ancho y alto.
        //posicionamos los astroides al azar

        for (Grafico obstaculo : obstaculos) {
            do{
                obstaculo.setPosX(Math.random() * (ancho - obstaculo.getAncho()));

                obstaculo.setPosY(Math.random() * (alto - obstaculo.getAlto()));

            }while(obstaculo.distancia(personaje)< (ancho+alto)/4);
        }
        //posicionamos la nave en el centro de la pantalla
        personaje.setPosX((ancho - personaje.getAncho()) /2);
        personaje.setPosY((alto - personaje.getAlto()) /2);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        for (Grafico obstaculo : obstaculos) {

            obstaculo.dibujaGrafico(canvas);

        }
        personaje.dibujaGrafico(canvas);
    }
}
