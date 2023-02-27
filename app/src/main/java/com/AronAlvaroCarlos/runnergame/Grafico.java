package com.AronAlvaroCarlos.runnergame;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;
import java.lang.Math;

public class Grafico {
    private Drawable drawable; // Imagen que dibujaremos

    private double posX, posY; // Posición

    private double incX, incY; // Velocidad desplazamiento

    private int angulo, rotacion;// Ángulo y velocidad rotación

    final int ancho, alto; // Dimensiones de la imagen

    final int radioColision; // Para determinar colisión

    // Donde dibujamos el gráfico (usada en view.ivalidate)

    final View view;

// Para determinar el espacio a borrar (view.ivalidate)???


    private double posInicial;

    public double getPosInicial() {
        return posInicial;
    }

    public void setPosInicial(double posInicial) {
        this.posInicial = posInicial;
    }

    public final int MAX_VELOCIDAD = 20;


    public Grafico(View view, Drawable drawable) {
        this.view = view;
        this.drawable = drawable;

        //Permite conseguir la anchura de la imagen mediante la clase del Drawable
        ancho = drawable.getIntrinsicWidth();
        //Permite conseguir la altura de la imagen mediante la clase del Drawable
        alto = drawable.getIntrinsicHeight();

        //
        radioColision = (alto + ancho) / 22;
    }


    public void dibujaGrafico(Canvas canvas) {

        //Posicionamiento de la imagen
        int x = (int) (posX + ancho / 4);

        int y = (int) (posY + alto / 4);

        //Los parámetros left y top determinan la posición
        // del Drawable, mientras que los parámetros right
        // y bottom determinan su tamaño.
        drawable.setBounds((int) posX, (int) posY, (int) posX + ancho,
                (int) posY + alto);
        //Guarda el estado actual del canvas
        canvas.save();

        canvas.rotate((float) angulo, (float) x, (float) y);
        drawable.draw(canvas);

        canvas.restore();//??? vuelve al ultimo canvas.save()

        int rInval = (int) Math.hypot(ancho, alto) / 2 + MAX_VELOCIDAD;

        view.invalidate(x - rInval, y - rInval, x + rInval, y + rInval);

    }

    public void incrementaPos(double factor) {
        posX += incX * factor*5;
        int numeroAleatorio = (int) (Math.random()*1000+0);
        // Si salimos de la pantalla, corregimos posición

        if (posX < -(float)ancho / 2) {
            posX = view.getWidth()+ numeroAleatorio;
        }

        posY += incY * factor;

       angulo += rotacion * factor; // Actualizamos ángulo

    }
    public void incrementaPosNave(double factor) {
        posX += incX * factor*2;
        int numeroAleatorio = (int) (Math.random()*5000+0);
        // Si salimos de la pantalla, corregimos posición

        if (posX < (float)-ancho / 2) {
            posX = view.getWidth()+ numeroAleatorio;
        }



        posY += incY * factor;




        angulo += rotacion * factor; // Actualizamos ángulo

    }

    public void salto() {

        if(posY<posInicial){
            posY += 20;
            rotacion+=5;
            angulo+=18.4;
        }



    }
    public int returnWidth(){
       return view.getWidth();
    }

    public void rotacionDisparo() {
            angulo += .3;
    }



    public double distancia(Grafico g) {

        return Math.hypot(posX - g.posX, posY - g.posY);

    }

    public boolean verificaColision(Grafico g) {

        return (distancia(g)-1 < (radioColision + g.radioColision));

    }

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    public double getPosX() {
        return posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }


    public void setIncX(double incX) {
        this.incX = incX;
    }






    public int getAncho() {
        return ancho;
    }



    public int getAlto() {
        return alto;
    }










}
