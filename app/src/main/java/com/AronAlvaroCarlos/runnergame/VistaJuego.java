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


    ////// THREAD Y TIEMPO //////

    // Thread encargado de procesar el juego
    private ThreadJuego thread = new ThreadJuego();
    // Cada cuanto queremos procesar cambios (ms)
    private static int PERIODO_PROCESO = 50;
    // Cuando se realizó el último proceso
    private long ultimo_Proceso = 0;


    /*
    private int giroNave; // Incremento de dirección
    private float aceleracionNave; // aumento de velocidad
    // Incremento estándar de giro y aceleración
    private static final int PASO_GIRO_NAVE = 5;
    private static final float PASO_ACELERACION_NAVE = 0.5f;
    */

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
        //cuanto mayor sea más se acerca a la izquierda
        personaje.setPosX((ancho - personaje.getAncho()) /13);
        //Cuanta más se acerque al 1 más se acerca abajo
        personaje.setPosY((alto - personaje.getAlto()) /1.2);

        //posicionamos el cactus en  pantalla
        cactus.setPosX((ancho - cactus.getAncho()) /1);
        cactus.setPosY((alto - cactus.getAlto()) /1.2);
        //posicionamos el avion en  pantalla
        avion.setPosX((ancho - avion.getAncho()) /1);
        avion.setPosY((alto - avion.getAlto()) /4);
    }


    protected  void actualizaFisica(){
        long ahora=System.currentTimeMillis();
        //salir del periodo de proceso no se ha cumplido
        if (ultimo_Proceso+PERIODO_PROCESO>ahora){
            return;
        }


        //Para una ejecucion en tiempo real
        //calculamos el factor de movimiento

        double factorMov= (ahora-ultimo_Proceso)/PERIODO_PROCESO;

        ultimo_Proceso=ahora;//para la proxima vez

        //Actualizamos velocidad y dirección de la nave a partir de
        // giroNave y aceleracionNave (según la entrada del jugador)
        personaje.setAngulo((int) (personaje.getAngulo()+giroNave*factorMov)); double nIncX = nave.getIncX() + aceleracionNave *
        Math.cos (Math.toRadians (nave.getAngulo()))*factorMov;
        double nIncY= nave.getIncY() + aceleracionNave Math.sin(Math.toRadians (nave.getAngulo()))*factorMov;
        //Actualizamos si el módulo de la velocidad //no excede el máximo
        if (Math.hypot (nIncX, nIncY)<=MAX_VELOCIDAD_NAVE){
            personaje.setIncX(nIncX);
            personaje.setIncY(nIncY);
        }
        personaje.incrementaPos (factorMov); //Actualizamos posición
            cactus.incrementaPos (factorMov);

    }
    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        personaje.dibujaGrafico(canvas);
        avion.dibujaGrafico(canvas);
        cactus.dibujaGrafico(canvas);

    }
}
