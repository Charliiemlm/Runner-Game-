package com.AronAlvaroCarlos.runnergame;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

public class VistaJuego extends View  implements View.OnTouchListener{
    private int player1Score = 0;
    private int disparosRestantes=8;

    private Context context;

    private Grafico personaje, casper,avion,muerte, disparo; // Gráficos

    ////// THREAD Y TIEMPO //////

    // Thread encargado de procesar el juego
    private ThreadJuego thread = new ThreadJuego();
    // Cada cuanto queremos procesar cambios (ms)
    private static int PERIODO_PROCESO = 55;
    // Cuando se realizó el último proceso
    private long ultimo_Proceso = 0;

    boolean disparoActivo=false;

    int puntos=0;

    public VistaJuego(Context context, AttributeSet attrs) {

        super(context, attrs);
        this.context=context;
        setOnTouchListener(this);

        Drawable drawablePersonaje, drawableCasper, drawableAvion , drawablemuerte, drawableDisparo;


        //Instanciando Personaje
        drawablePersonaje = context.getResources().getDrawable(R.drawable.personaje);
        personaje = new Grafico(this, drawablePersonaje);
        personaje.setIncX(15);


        //instanciando disparo
        drawableDisparo = context.getResources().getDrawable(R.drawable.disparo);
        disparo = new Grafico(this,drawableDisparo);
        disparo.setIncX(8);



        //Instanciando los casper
        drawableCasper = context.getResources().getDrawable(
                R.drawable.casper);
        casper = new Grafico(this, drawableCasper);
        //casper.setIncY(4);
        casper.setIncX(-4);

        //Instanciando los muerte
        drawablemuerte = context.getResources().getDrawable(
                R.drawable.muerte);
        muerte = new Grafico(this, drawablemuerte);
        //casper.setIncY(4);
        muerte.setIncX(-4);



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



        //posicionamos el casper en  pantalla
        casper.setPosX((ancho - casper.getAncho()) /1);
        casper.setPosY((alto - casper.getAlto()) /1.18);


        //posicionamos el casper en  pantalla
        muerte.setPosX((ancho - muerte.getAncho()) /-1);
        muerte.setPosY((alto - muerte.getAlto()) /1.1);


        //posicionamos el avion en  pantalla
        avion.setPosX((ancho - avion.getAncho()) /-5);
        avion.setPosY((alto - avion.getAlto()) /1.9);




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

        // Actualizamos posición
        casper.incrementaPos (factorMov);
        avion.incrementaPosNave(factorMov);
        muerte.incrementaPos(factorMov);
        personaje.salto();


        //comprobar si el disparo se ha ido de la pantalla
        if(disparo.getPosX()>=disparo.returnWidth()){
            disparo.setPosX(personaje.getPosX()-500);
            disparoActivo=false;
        }

        //si el disparo esta activo se le añade movimiento
        if (disparoActivo){
            disparo.incrementaPos(factorMov);
            disparo.rotacionDisparo();

        }

        if(disparo.verificaColision(casper) ){
            casper.setPosX(casper.getPosX()+1000);
            disparoActivo=false;
            disparo.setPosX(personaje.getPosX()-500);
            System.out.println("-------------COLISION CON CASTER");

        }
        if(disparo.verificaColision(avion)){
            avion.setPosX(avion.getPosX()+1000);
            disparoActivo=false;
            disparo.setPosX(personaje.getPosX()-500);
        }
        if(disparo.verificaColision(muerte)){
            muerte.setPosX(muerte.getPosX()+1000);
            disparoActivo=false;
            disparo.setPosX(personaje.getPosX()-500);
            System.out.println("-------------COLISION CON MUERTE");


        }
         if(disparo.verificaColision(avion)){
            avion.setPosX(avion.getPosX()+1000);
            disparoActivo=false;
            disparo.setPosX(personaje.getPosX()-500);
        }
        player1Score ++;



    }

    public void activaDisparo(){
        if(disparo.getPosX()>=personaje.getPosX()-500 && disparosRestantes>0){
            disparo.setPosX(personaje.getPosX());
            disparo.setPosY(personaje.getPosY());
            disparoActivo=true;
            disparosRestantes--;
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
        casper.dibujaGrafico(canvas);
        muerte.dibujaGrafico(canvas);

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
                if (personaje.verificaColision(casper) || personaje.verificaColision(avion)
                        || personaje.verificaColision(muerte)) {
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