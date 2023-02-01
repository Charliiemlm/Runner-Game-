package com.AronAlvaroCarlos.runnergame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import java.util.Vector;

public class VistaJuego {

    // //// NAVE //////

    private Grafico nave;// Gráfico de la nave

    private int giroNave; // Incremento de dirección

    private float aceleracionNave; // aumento de velocidad

    // Incremento estándar de giro y aceleración

    private static final int PASO_GIRO_NAVE = 5;

    private static final float PASO_ACELERACION_NAVE = 0.5f;

    // //// ASTEROIDES //////

    private Vector<Grafico> Asteroides; // Vector con los Asteroides

    private int numAsteroides = 5; // Número inicial de asteroides

    private int numFragmentos = 3; // Fragmentos en que se divide

    public VistaJuego(Context context, AttributeSet attrs) {

        super(context, attrs);

        Drawable drawableNave, drawableAsteroide, drawableMisil;
        //
        drawableNave = context.getResources().getDrawable(R.drawable.nave);
        nave = new Grafico(this, drawableNave);


        //Instaciando los Asteroides
        drawableAsteroide = context.getResources().getDrawable(
                R.drawable.asteroide1);

        Asteroides = new Vector<Grafico>();

        for (int i = 0; i < numAsteroides; i++) {

            Grafico asteroide = new Grafico(this, drawableAsteroide);

            asteroide.setIncY(Math.random() * 4 - 2);

            asteroide.setIncX(Math.random() * 4 - 2);

            asteroide.setAngulo((int) (Math.random() * 360));

            asteroide.setRotacion((int) (Math.random() * 8 - 4));

            Asteroides.add(asteroide);

        }

    }

    @Override
    protected void onSizeChanged(int ancho, int alto, int ancho_anter,
                                 int alto_anter) {

        super.onSizeChanged(ancho, alto, ancho_anter, alto_anter);

        // Una vez que conocemos nuestro ancho y alto.
        //posicionamos los astroides al azar

        for (Grafico asteroide : Asteroides) {
            do{
                asteroide.setPosX(Math.random() * (ancho - asteroide.getAncho()));

                asteroide.setPosY(Math.random() * (alto - asteroide.getAlto()));

            }while(asteroide.distancia(nave)< (ancho+alto)/4);
        }
        //posicionamos la nave en el centro de la pantalla
        nave.setPosX((ancho - nave.getAncho()) /2);
        nave.setPosY((alto - nave.getAlto()) /2);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        for (Grafico asteroide : Asteroides) {

            asteroide.dibujaGrafico(canvas);

        }
        nave.dibujaGrafico(canvas);
    }
}
