package com.AronAlvaroCarlos.runnergame;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class VistaJuego extends View  {

    private GameOverListener gameOverListener;
    private Context context;

    private Grafico personaje, cactus,avion,planta, planta2, disparo; // Gráficos

    ////// THREAD Y TIEMPO //////

    // Thread encargado de procesar el juego
    private ThreadJuego thread = new ThreadJuego();
    // Cada cuanto queremos procesar cambios (ms)
    private static int PERIODO_PROCESO = 50;
    // Cuando se realizó el último proceso
    private long ultimo_Proceso = 0;

    boolean disparoActivo=false;

    int puntos=0;
    private int giroDisparo; // Incremento de dirección
    private float aceleracionDisparo; // aumento de velocidad
    // Incremento estándar de giro y aceleración
    private static final int PASO_GIRO_DISPARO= 5;
    private static final float PASO_ACELERACION_DISPARO = 0.5f;

    public VistaJuego(Context context, @Nullable AttributeSet attrs, GameOverListener gameOverListener) {
        super(context, attrs);
        this.gameOverListener = gameOverListener;

    }


    public VistaJuego(Context context, AttributeSet attrs) {

        super(context, attrs);

        Drawable drawablePersonaje, drawableCactus, drawableAvion , drawablePlanta, drawablePlanta2, drawableDisparo;


        //Instanciando Personaje
        drawablePersonaje = context.getResources().getDrawable(R.drawable.personaje);
        personaje = new Grafico(this, drawablePersonaje);

        personaje.setIncX(5);


        //instanciando disparo
        drawableDisparo = context.getResources().getDrawable(R.drawable.disparo);
        disparo = new Grafico(this,drawableDisparo);
        disparo.setIncX(15);



        //Instanciando los cactus
        drawableCactus = context.getResources().getDrawable(
                R.drawable.cactus);
         cactus = new Grafico(this, drawableCactus);
        //cactus.setIncY(4);
        cactus.setIncX(-4);

        drawablePlanta2 = context.getResources().getDrawable(
                R.drawable.plant);

        //Instanciando los planta
        drawablePlanta = context.getResources().getDrawable(
                R.drawable.plant);
        planta = new Grafico(this, drawablePlanta);
        //cactus.setIncY(4);
        planta.setIncX(-4);

        drawablePlanta2 = context.getResources().getDrawable(
                R.drawable.plant);
        planta2 = new Grafico(this, drawablePlanta2);
        //cactus.setIncY(4);
        planta2.setIncX(-4);




        //Instanciando el avion
        drawableAvion = context.getResources().getDrawable(
                R.drawable.avion);
         avion = new Grafico(this, drawableAvion);
            //avion.setIncY(Math.random() * 4 - 2);
            avion.setIncX(-6);

    }

    public VistaJuego(Context context) {
        super(context);
        this.context=context;
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
        personaje.setPosInicial(personaje.getPosY());



        //posicionamos el cactus en  pantalla
        cactus.setPosX((ancho - cactus.getAncho()) /1);
        cactus.setPosY((alto - cactus.getAlto()) /1.2);


        //posicionamos el cactus en  pantalla
        planta.setPosX((ancho - planta.getAncho()) /-1);
        planta.setPosY((alto - planta.getAlto()) /1.2);

        planta2.setPosX((ancho - planta.getAncho()) /-10);
        planta2.setPosY((alto - planta.getAlto()) /1.2);


        //posicionamos el avion en  pantalla
        avion.setPosX((ancho - avion.getAncho()) /-5);
        avion.setPosY((alto - avion.getAlto()) /4);




        ultimo_Proceso=System.currentTimeMillis();
        thread.start();
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


        //Actualizamos si el módulo de la velocidad no excede el máximo



           // personaje.incrementaPos (factorMov);
           // Actualizamos posición
            cactus.incrementaPos (factorMov);
            avion.incrementaPosNave(factorMov);
            planta.incrementaPos(factorMov);
            planta2.incrementaPos(factorMov);
            personaje.salto();


            if (disparoActivo){
                disparo.incrementaPos(factorMov);
                disparo.rotacionDisparo();

            }

            if(disparo.verificaColision(cactus) ){
                cactus.setPosX(cactus.getPosX()+1000);
                disparoActivo=false;
                disparo.setPosX(personaje.getPosX());

             }else if(disparo.verificaColision(avion)){
                avion.setPosX(avion.getPosX()+1000);
                disparoActivo=false;
                disparo.setPosX(personaje.getPosX());

            }
            else if(disparo.verificaColision(planta)){
                planta.setPosX(planta.getPosX()+1000);
                disparoActivo=false;
                disparo.setPosX(personaje.getPosX());

            }else if(disparo.verificaColision(planta2)){
                planta2.setPosX(planta2.getPosX()+1000);
                disparoActivo=false;
                disparo.setPosX(personaje.getPosX());

            }


    }

    public void activaDisparo(){
        disparo.setPosX(personaje.getPosX()-30);
        disparo.setPosY(personaje.getPosY()-20);
        disparoActivo=true;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        personaje.dibujaGrafico(canvas);
        avion.dibujaGrafico(canvas);
        cactus.dibujaGrafico(canvas);
        planta2.dibujaGrafico(canvas);
        planta.dibujaGrafico(canvas);

        planta.dibujaGrafico(canvas);

        if(disparoActivo){
            disparo.dibujaGrafico(canvas);

        }


    }

    public class ThreadJuego extends Thread{
        @Override
        public void run() {
            while(true){
                actualizaFisica();

                 if (personaje.verificaColision(cactus)){
                     if (gameOverListener != null)
                     gameOverListener.onGameOver(puntos);
                     break;
                }





            }
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_RIGHT:
                personaje.setPosX(personaje.getPosX() + 10);
                break;
            case KeyEvent.KEYCODE_DPAD_LEFT:
                personaje.setPosX(personaje.getPosX() - 10);
                break;
            case KeyEvent.KEYCODE_D:

                if(disparoActivo==false){
                    activaDisparo();

                }
                break;
            case KeyEvent.KEYCODE_SPACE:
                if(personaje.getPosY()>=personaje.getPosInicial()) {
                    personaje.setPosY(personaje.getPosY() - 400);

                }

                break;
        }
        return super.onKeyDown(keyCode, event);
    }



















}
