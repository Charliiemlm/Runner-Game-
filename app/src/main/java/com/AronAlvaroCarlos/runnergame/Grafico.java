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

    private int ancho, alto; // Dimensiones de la imagen

    private int radioColision; // Para determinar colisión

    // Donde dibujamos el gráfico (usada en view.ivalidate)

    private View view;

    // Para determinar el espacio a borrar (view.ivalidate)???

    private double posInicial;

    public double getPosInicial() {
        return posInicial;
    }

    public void setPosInicial(double posInicial) {
        this.posInicial = posInicial;
    }

    public static final int MAX_VELOCIDAD = 20;

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
    public Grafico(){

    }
    public void dibujaPuntos(Canvas canvas){

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

        if (posX < -ancho / 2) {
            posX = view.getWidth()+ numeroAleatorio;
        }

        /*if (posX > view.getWidth() - ancho / 2) {
            posX = -ancho / 2;

        }*/

        posY += incY * factor;

        /*if (posY < -alto / 2) {
            posY = view.getHeight() - alto / 2;
        }

        if (posY > view.getHeight() - alto / 2) {
            posY = -alto / 2;
        }*/



       angulo += rotacion * factor; // Actualizamos ángulo

    }
    public void incrementaPosNave(double factor) {
        posX += incX * factor*2;
        int numeroAleatorio = (int) (Math.random()*5000+0);
        // Si salimos de la pantalla, corregimos posición

        if (posX < -ancho / 2) {
            posX = view.getWidth()+ numeroAleatorio;
        }

        /*if (posX > view.getWidth() - ancho / 2) {
            posX = -ancho / 2;

        }*/

        posY += incY * factor;

        /*if (posY < -alto / 2) {
            posY = view.getHeight() - alto / 2;
        }

        if (posY > view.getHeight() - alto / 2) {
            posY = -alto / 2;
        }*/



        angulo += rotacion * factor; // Actualizamos ángulo

    }
    public void salto() {


        if(posY<posInicial){
            posY += 20;
            rotacion+=5;
            angulo+=18.4;
        }



    }
    public void rotacionDisparo() {

            angulo += 5;

    }
    public void incrementaPosMain(double factor) {
        //posX += incX * factor;
        // Si salimos de la pantalla, corregimos posición

        /*if(posX < -ancho / 2) {
            posX = view.getWidth() - ancho /2;
        }*/
        if (posX > view.getWidth()/2) {
            posX = view.getWidth()/2;

        }

        posY += incY * factor;

        /*if (posY < -alto / 2) {
            posY = view.getHeight() - alto / 2;
        }

        if (posY > view.getHeight() - alto / 2) {
            posY = -alto / 2;
        }*/


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

    public double getIncX() {
        return incX;
    }

    public void setIncX(double incX) {
        this.incX = incX;
    }

    public double getIncY() {
        return incY;
    }

    public void setIncY(double incY) {
        this.incY = incY;
    }

    public int getAngulo() {
        return angulo;
    }

    public void setAngulo(int angulo) {
        this.angulo = angulo;
    }

    public int getRotacion() {
        return rotacion;
    }

    public void setRotacion(int rotacion) {
        this.rotacion = rotacion;
    }

    public int getAncho() {
        return ancho;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public int getAlto() {
        return alto;
    }

    public void setAlto(int alto) {
        this.alto = alto;
    }

    public int getRadioColision() {
        return radioColision;
    }

    public void setRadioColision(int radioColision) {
        this.radioColision = radioColision;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public static int getMaxVelocidad() {
        return MAX_VELOCIDAD;
    }

}
