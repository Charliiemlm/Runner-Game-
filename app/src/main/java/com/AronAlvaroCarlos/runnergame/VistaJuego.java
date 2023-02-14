package com.AronAlvaroCarlos.runnergame;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.net.ConnectException;

public class VistaJuego extends View  implements View.OnTouchListener{
    private int player1Score = 0;


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



    public VistaJuego(Context context, AttributeSet attrs) {

        super(context, attrs);
        this.context=context;
        setOnTouchListener(this);

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
        personaje.setPosY((alto - personaje.getAlto()) /1.1);
        personaje.setPosInicial(personaje.getPosY());



        //posicionamos el cactus en  pantalla
        cactus.setPosX((ancho - cactus.getAncho()) /1);
        cactus.setPosY((alto - cactus.getAlto()) /1.15);


        //posicionamos el cactus en  pantalla
        planta.setPosX((ancho - planta.getAncho()) /-1);
        planta.setPosY((alto - planta.getAlto()) /1.1);

        planta2.setPosX((ancho - planta.getAncho()) /-10);
        planta2.setPosY((alto - planta.getAlto()) /1.1);


        //posicionamos el avion en  pantalla
        avion.setPosX((ancho - avion.getAncho()) /-5);
        avion.setPosY((alto - avion.getAlto()) /2.2);




        ultimo_Proceso=System.currentTimeMillis();
        thread.start();
    }


    protected void actualizaFisica(){
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


        //comprobar si el disparo se ha ido de la pantalla
        if(disparo.getPosX()>=disparo.returnWidth()){
            disparo.setPosX(personaje.getPosX()-1000);
            disparoActivo=false;
        }

        if (disparoActivo){
            disparo.incrementaPos(factorMov);
            disparo.rotacionDisparo();
        }
        if(disparo.verificaColision(cactus) ){
            cactus.setPosX(cactus.getPosX()+1000);
            disparoActivo=false;
            disparo.setPosX(personaje.getPosX()-1000);

        }else if(disparo.verificaColision(avion)){
            avion.setPosX(avion.getPosX()+1000);
            disparoActivo=false;
            disparo.setPosX(personaje.getPosX()-1000);

        }
        else if(disparo.verificaColision(planta)){
            planta.setPosX(planta.getPosX()+1000);
            disparoActivo=false;
            disparo.setPosX(personaje.getPosX()-1000);


        }else if(disparo.verificaColision(planta2)){
            planta2.setPosX(planta2.getPosX()+1000);
            disparoActivo=false;
            disparo.setPosX(personaje.getPosX()-1000);
        }
        else if(disparo.verificaColision(avion)){
            avion.setPosX(avion.getPosX()+1000);
            disparoActivo=false;
            disparo.setPosX(personaje.getPosX()-1000);
        }
        player1Score ++;



    }

    public void activaDisparo(){
        if(disparo.getPosX()>=personaje.getPosX()-1000){
            disparo.setPosX(personaje.getPosX()-30);
            disparo.setPosY(personaje.getPosY()-20);
            disparoActivo=true;
        }

    }
    /* private void drawScoresOnCanvas(Canvas canvas) {
         Paint paint = new Paint();
         paint.setTextSize(40);
         paint.setColor(Color.WHITE);
         paint.setTextAlign(Paint.Align.CENTER);

         // Esto es pa dibujar al la tabla
         float x1 = canvas.getWidth() / 4;
         float y1 = canvas.getHeight()/6;
         canvas.drawText(player1Score, x1, y1, paint);
         Bitmap logo = BitmapFactory.decodeResource(getResources(), R.drawable.danny2);
         float logoX = canvas.getWidth() / 6 - logo.getWidth() / 2;
         float logoY = 0;
         canvas.drawBitmap(logo, logoX, logoY, null);
     }*/
    private void drawScoresOnCanvas(Canvas canvas) {
        Paint paint = new Paint();
        paint.setTextSize(80);
        paint.setColor(Color.WHITE);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);
        // Rectangulo con border rojo
        paint.setColor(Color.rgb(255, 0, 0));
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(canvas.getWidth() / 1 - 250, canvas.getHeight() / 6 - 60,
                canvas.getWidth() /2 + 250, canvas.getHeight() / 6 + 60, paint);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        canvas.drawRect(canvas.getWidth() / 1 - 250, canvas.getHeight() / 6 - 60,
                canvas.getWidth() /2 + 250, canvas.getHeight() / 6 + 60, paint);
        //Orange but it's not likely to stay this way, better dark??
        paint.setColor(Color.rgb(	255, 69, 0));
        paint.setStyle(Paint.Style.STROKE);
        paint.setShadowLayer(5, 10, 10, 0xff000000);
        canvas.drawRect(canvas.getWidth() / 1 - 250, canvas.getHeight() / 6 - 60,
                canvas.getWidth() /2 + 250, canvas.getHeight() / 6 + 60, paint);


        // Hay que dar más detalles aquí, color naranja
      /* paint.setColor(Color.rgb(255, 165, 0));
       canvas.drawLine(canvas.getWidth() / 4 - 75, canvas.getHeight() / 6 - 30,
               canvas.getWidth() * 3 / 4 + 75, canvas.getHeight() / 6 - 30, paint);
       canvas.drawLine(canvas.getWidth() / 4 - 75, canvas.getHeight() / 6 + 30,
               canvas.getWidth() * 3 / 4 + 75, canvas.getHeight() / 6 + 30, paint);*/

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);

        // Player 1 score
        float x1 = canvas.getWidth() / 1 - 600;
        float y1 = canvas.getHeight()/7 + 50;
        canvas.drawText(String.valueOf(player1Score), x1, y1, paint);
        float x2 = canvas.getWidth() / 1 - 400;
        float y2 = canvas.getHeight()/7 + 50;
        canvas.drawText(" Pts", x2, y2, paint);
        //canvas.drawPaint(paint);
        // Draw the "Danny" logo
        Bitmap logo = BitmapFactory.decodeResource(getResources(), R.drawable.danny2);
        float logoX = canvas.getWidth() / 2 - logo.getWidth() / 2 +240;
        float logoY = 50;
        canvas.drawBitmap(logo, logoX, logoY, null);
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
        drawScoresOnCanvas(canvas);


    }

    public class ThreadJuego extends Thread{
        @Override
        public void run() {
            while(true) {
                actualizaFisica();
                // Utilice un manejador para llamar a updatePoints cada milisegundo
                if (personaje.verificaColision(cactus) || personaje.verificaColision(avion) || personaje.verificaColision(planta) || personaje.verificaColision(planta2)) {
                    //pa que no siga actualizando los puntos
                    //personaje=null;

                    Intent intent = new Intent(context, GameOver.class);
                    intent.putExtra("points", player1Score);
                    context.startActivity(intent);

                    ((Activity) context).finish();
                    interrupt();
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
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (motionEvent.getX() > view.getWidth() / 2) {
                    personaje.setPosX(personaje.getPosX() + 10);
                } else {
                    personaje.setPosX(personaje.getPosX() - 10);
                }
                break;
            case MotionEvent.ACTION_UP:
                if (motionEvent.getY() < view.getHeight() / 2) {
                    if (personaje.getPosY() >= personaje.getPosInicial()) {
                        personaje.setPosY(personaje.getPosY() - 400);
                    }
                } else if (motionEvent.getX() > view.getWidth() / 2) {
                    activaDisparo();
                }
                break;
        }
        return true;
    }


}