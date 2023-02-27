package com.AronAlvaroCarlos.runnergame;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.provider.Settings;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.DrawableRes;

public class VistaJuego extends View  {
    private int player1Score = 0;
    public int disparosRestantes=5;


    private MediaPlayer jump;
    private MediaPlayer energy;
    private MediaPlayer death;
    private final Context context;
    private Grafico personaje, casper,volador,muerte, disparo, mosca; // Gráficos
    // Thread encargado de procesar el juego
    private final ThreadJuego thread = new ThreadJuego();
    // Cada cuanto queremos procesar cambios (ms)
    private static int PERIODO_PROCESO = 55;
    // Cuando se realizó el último proceso
    private long ultimo_Proceso = 0;
    boolean disparoActivo=false;

    @SuppressLint("UseCompatLoadingForDrawables")
    public VistaJuego(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;

        Drawable drawablePersonaje, drawableCasper,
                drawablevolador , drawablemuerte, drawableDisparo , drawableMosca;

        //Instanciando Personaje
        drawablePersonaje = context.getResources().getDrawable(R.drawable.personaje);
        personaje = new Grafico(this, drawablePersonaje);
        personaje.setIncX(15);

        //instanciando disparo
        drawableDisparo = context.getResources().getDrawable(R.drawable.disparo);
        disparo = new Grafico(this,drawableDisparo);
        disparo.setIncX(9);

        //Instanciando los casper
        drawableCasper = context.getResources().getDrawable(
                R.drawable.casper);
        casper = new Grafico(this, drawableCasper);
        casper.setIncX(-4/*(personaje.getMaxVelocidad())*/);

        //Instanciando los muerte
        drawablemuerte = context.getResources().getDrawable(
                R.drawable.muerte);
        muerte = new Grafico(this, drawablemuerte);
        muerte.setIncX(-4/*(personaje.getMaxVelocidad())*/);

        //Instanciando Mosca
        drawableMosca = context.getResources().getDrawable(
                R.drawable.eyeball_sprite);
        mosca = new Grafico(this, drawableMosca);
        mosca.setIncX(-5 /*(personaje.getMaxVelocidad())*/);

        //Instanciando el volador
        drawablevolador = context.getResources().getDrawable(
                R.drawable.mosca);
        volador = new Grafico(this, drawablevolador);
        volador.setIncX(-10/*(personaje.getMaxVelocidad())*/);


        //sonidos
        jump=MediaPlayer.create(context.getApplicationContext(), R.raw.jump2);
        energy=MediaPlayer.create(context.getApplicationContext(), R.raw.energy);
        death=MediaPlayer.create(context.getApplicationContext(),R.raw.death);
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
        personaje.setPosX((ancho - personaje.getAncho()) / (float)13);

        //Cuanta más se acerque al 1 más se acerca abajo
        personaje.setPosY((alto - personaje.getAlto()) /1.1);
        personaje.setPosInicial(personaje.getPosY());


        //posicionamos el casper en  pantalla
        casper.setPosX((ancho - casper.getAncho()));
        casper.setPosY((alto - casper.getAlto()) /1.18);

        //posicionamos el casper en  pantalla
        muerte.setPosX((ancho - muerte.getAncho()) /(float)-1);
        muerte.setPosY((alto - muerte.getAlto()) /1.1);


        //posicionamos el casper en  pantalla
        mosca.setPosX((ancho - mosca.getAncho()) / (float)-1);
        mosca.setPosY((alto - mosca.getAlto()) /1.3);


        //posicionamos el volador en  pantalla
        volador.setPosX((ancho - volador.getAncho()) / (float)-5);
        volador.setPosY((alto - volador.getAlto()) /1.777);



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

        double factorMov= (ahora-ultimo_Proceso)/ (float)PERIODO_PROCESO;

        ultimo_Proceso=ahora;//para la proxima vez

        // Actualizamos posición
        casper.incrementaPos (factorMov);
        volador.incrementaPosNave(factorMov);
        muerte.incrementaPos(factorMov);
        mosca.incrementaPos(factorMov);
        personaje.salto();

        //si el disparo esta activo se le añade movimiento
        if (disparoActivo){
            disparo.incrementaPos(factorMov);
            disparo.rotacionDisparo();
        }

        //comprobar si el disparo se ha ido de la pantalla
        if(disparo.getPosX()>=disparo.returnWidth()){
            disparo.setPosX(personaje.getPosX()-500);
            disparoActivo=false;
        }

        //colisiones del disparo
        if(disparo.verificaColision(casper) ){
            casper.setPosX(casper.returnWidth()+(int) (Math.random()*500+100));
            disparoActivo=false;
            disparo.setPosX(personaje.getPosX()-500);
        }
        if(disparo.verificaColision(volador)){
            volador.setPosX(volador.returnWidth()+(int) (Math.random()*500+100));
            disparoActivo=false;
            disparo.setPosX(personaje.getPosX()-500);
        }
        if(disparo.verificaColision(muerte)){
            muerte.setPosX(muerte.returnWidth()+(int) (Math.random()*500+100));
            disparoActivo=false;
            disparo.setPosX(personaje.getPosX()-500);
        }
         if(disparo.verificaColision(volador)){
            volador.setPosX(volador.returnWidth()+(int) (Math.random()*500+100));
            disparoActivo=false;
            disparo.setPosX(personaje.getPosX()-500);
        }
        if(disparo.verificaColision(mosca)){
            mosca.setPosX(mosca.returnWidth()+(int) (Math.random()*500+100));
            disparoActivo=false;
            disparo.setPosX(mosca.getPosX()-500);
        }


         //sumamos puntuacion aqui ya que se se ejecuta continuamente
           player1Score ++;
    }

    public void activaDisparo(){
        //hola
        //filtro para evitar que se pueda spamear el disparo
        if(disparo.getPosX()<=personaje.getPosX() && disparosRestantes>0){
            disparo.setPosX(personaje.getPosX());
            disparo.setPosY(personaje.getPosY());
            disparoActivo=true;
            disparosRestantes--;
        }

    }

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
        canvas.drawRect(canvas.getWidth() - 250, canvas.getHeight() / (float)6 - 60,
                canvas.getWidth() /(float)2 + 250, canvas.getHeight() / (float)6 + 60, paint);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        canvas.drawRect(canvas.getWidth() - 250, canvas.getHeight() / (float)6 - 60,
                canvas.getWidth() /(float)2 + 250, canvas.getHeight() / (float)6 + 60, paint);
        //Orange but it's not likely to stay this way, better dark??
        paint.setColor(Color.rgb(	255, 69, 0));
        paint.setStyle(Paint.Style.STROKE);
        paint.setShadowLayer(5, 10, 10, 0xff000000);
        canvas.drawRect(canvas.getWidth() - 250, canvas.getHeight() / (float)6 - 60, canvas.getWidth() /(float)2 + 250, canvas.getHeight() / (float)6 + 60, paint);


        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);

        // Player 1 score
        float x1 = canvas.getWidth() - 600;
        float y1 = canvas.getHeight()/ (float)7 + 50;
        canvas.drawText(String.valueOf(player1Score), x1, y1, paint);
        float x2 = canvas.getWidth() - 400;
        float y2 = canvas.getHeight()/(float)7 + 50;
        canvas.drawText(" Pts", x2, y2, paint);

        // Draw the "Danny" logo
        Bitmap logo = BitmapFactory.decodeResource(getResources(), R.drawable.danny2);
        float logoX = canvas.getWidth() / (float)2 - logo.getWidth() / (float)2 +240;
        float logoY = 50;
        canvas.drawBitmap(logo, logoX, logoY, null);
    }
    private void shoots(Canvas canvas){
        Paint paint = new Paint();
        paint.setTextSize(80);
        paint.setColor(Color.WHITE);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);
        // Rectangulo con border rojo
        paint.setColor(Color.	rgb(40, 59, 142));
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(canvas.getWidth()/12, canvas.getHeight() / (float)12,
                canvas.getWidth() /(float)3, canvas.getHeight() / (float)4, paint);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        canvas.drawRect(canvas.getWidth()/12, canvas.getHeight() / (float)13,
                canvas.getWidth() /(float)3, canvas.getHeight() / (float)6 + 60, paint);
        //Orange but it's not likely to stay this way, better dark??
        paint.setColor(Color.rgb(	255, 69, 0));
        paint.setStyle(Paint.Style.STROKE);
        paint.setShadowLayer(5, 10, 10, 0xff000000);
        canvas.drawRect(canvas.getWidth()/12, canvas.getHeight() / (float)12,
                canvas.getWidth() /(float)3, canvas.getHeight() / (float)6 + 60, paint);

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.WHITE);

        // Player 1 score
        float x1 = canvas.getWidth()/(float)3.5;
        float y1 = canvas.getHeight()/ (float)5.5;
        float x2 = canvas.getWidth()/5;
        float y2 = canvas.getHeight()/(float)5.5;
        if(disparosRestantes>0){
            canvas.drawText("Ammo: ", x2, y2, paint);
            canvas.drawText(String.valueOf(disparosRestantes), x1, y1, paint);
        }else{
            canvas.drawText("NO AMMO", x2, y2, paint);
        }
        //canvas.drawPaint(paint);
        // Draw the "shoots" logo
        Bitmap logo = BitmapFactory.decodeResource(getResources(), R.drawable.disparo);
        float logoX = canvas.getWidth() / (float)5 - logo.getWidth() / (float)0.5;
        float logoY = 50;
        canvas.drawBitmap(logo, logoX, logoY, null);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        personaje.dibujaGrafico(canvas);
        volador.dibujaGrafico(canvas);
        casper.dibujaGrafico(canvas);
        muerte.dibujaGrafico(canvas);
        mosca.dibujaGrafico(canvas);
        //si es true se pinta el disparo
        if(disparoActivo){
            disparo.dibujaGrafico(canvas);
        }
        //pintar puntuacion
        drawScoresOnCanvas(canvas);
        shoots(canvas);
    }

    public class ThreadJuego extends Thread{
        @Override
        public void run() {
            while(true) {
                actualizaFisica();
                // Utilice un manejador para llamar a updatePoints cada milisegundo
                if (personaje.verificaColision(casper) || personaje.verificaColision(volador)
                        || personaje.verificaColision(muerte)  || personaje.verificaColision(mosca)) {
                    death.start();
                    Intent intent = new Intent(context, GameOver.class);
                    intent.putExtra("points", player1Score);
                    context.startActivity(intent);
                    try {
                        Thread.sleep(500); // Espera 500 milisegundos para permitir que se actualice el diseño de la actividad GameOver.
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

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
                if (personaje.getAncho() < personaje.getPosX()/5) {
                    personaje.setPosX(personaje.getAncho()*5);
                }else{
                    personaje.setPosX(personaje.getPosX() + 10);
                }
                break;
            case KeyEvent.KEYCODE_DPAD_LEFT:
                if(personaje.getPosX() < -personaje.getAncho() / 2){
                    personaje.setPosX(personaje.getAncho()/2);
                }else{
                    personaje.setPosX(personaje.getPosX() - 10);
                }
                break;
            case KeyEvent.KEYCODE_D:
                if(!disparoActivo){
                    if(disparosRestantes>0){
                        System.out.println(disparosRestantes);
                        energy.start();
                    }
                    activaDisparo();
                }
                break;
            case KeyEvent.KEYCODE_SPACE:
                jump.start();
                if(personaje.getPosY()>=personaje.getPosInicial()) {

                   personaje.setPosY(personaje.getPosY() - 400);

                }
                break;
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getActionMasked();
        float  lastTouchX =0;
        float  lastTouchY=0;
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                // Se ha tocado la pantalla
                lastTouchX = event.getX();
                lastTouchY = event.getY();
                break;

            case MotionEvent.ACTION_MOVE:
                // Se está moviendo el dedo por la pantalla
                float deltaX = event.getX() - lastTouchX;
                lastTouchX = event.getX();
                float deltaY = event.getY() - lastTouchY;
                lastTouchY = event.getY();

                // Movimiento horizontal
                if (Math.abs(deltaX) > Math.abs(deltaY)) {
                    if (deltaX > 0) {
                        // Movimiento hacia la derecha
                        personaje.setPosX(personaje.getPosX() + 5);
                    } else {
                        // Movimiento hacia la izquierda
                        personaje.setPosX(personaje.getPosX() - 5);
                    }
                }
                break;

            case MotionEvent.ACTION_UP:
                // Se ha levantado el dedo de la pantalla
                if (event.getY() < getHeight() / 4) {
                    // Tocado en la parte superior de la pantalla (botón de disparo)
                    if (!disparoActivo) {
                        if (disparosRestantes > 0) {
                            System.out.println(disparosRestantes);
                            energy.start();
                        }
                        activaDisparo();
                    }
                } else {
                    // Tocado en otra parte de la pantalla (salto)
                    jump.start();
                    if (personaje.getPosY() >= personaje.getPosInicial()) {
                        personaje.setPosY(personaje.getPosY() - 400);
                    }
                }
                break;

            default:
                return super.onTouchEvent(event);
        }

        return true;
    }



    public int getDisparosRestantes() {
        return disparosRestantes;
    }

    public void setDisparosRestantes(int disparosRestantes) {
        this.disparosRestantes = disparosRestantes;
    }
}