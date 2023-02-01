package com.AronAlvaroCarlos.runnergame;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapBank {


    Bitmap background;
    Bitmap path;
    Bitmap[] player = new Bitmap[11];
    Bitmap[] playerJump = new Bitmap[16];
    Bitmap[] playerDead = new Bitmap[17];
    Bitmap box;
    Bitmap crystal;
    Bitmap iceBox;
    Bitmap snowMan;
    Bitmap stone;
    Bitmap tapToStart;

    public BitmapBank(Resources res) {


        background = BitmapFactory.decodeResource(res, R.drawable.background);
        background = scaleImage(background);

        //movimientos del jugados
        path = BitmapFactory.decodeResource(res, R.drawable.path);
        player[0] = BitmapFactory.decodeResource(res, R.drawable.run1);
        player[1] = BitmapFactory.decodeResource(res, R.drawable.run2);
        player[2] = BitmapFactory.decodeResource(res, R.drawable.run3);
        player[3] = BitmapFactory.decodeResource(res, R.drawable.run4);
        player[4] = BitmapFactory.decodeResource(res, R.drawable.run5);
        player[5] = BitmapFactory.decodeResource(res, R.drawable.run6);
        player[6] = BitmapFactory.decodeResource(res, R.drawable.run7);
        player[7] = BitmapFactory.decodeResource(res, R.drawable.run8);
        player[8] = BitmapFactory.decodeResource(res, R.drawable.run9);
        player[9] = BitmapFactory.decodeResource(res, R.drawable.run10);
        player[10] = BitmapFactory.decodeResource(res, R.drawable.run11);

        //saltos del jugador
        playerJump[0] = BitmapFactory.decodeResource(res, R.drawable.jump1);
        playerJump[1] = BitmapFactory.decodeResource(res, R.drawable.jump2);
        playerJump[2] = BitmapFactory.decodeResource(res, R.drawable.jump3);
        playerJump[3] = BitmapFactory.decodeResource(res, R.drawable.jump4);
        playerJump[4] = BitmapFactory.decodeResource(res, R.drawable.jump5);
        playerJump[5] = BitmapFactory.decodeResource(res, R.drawable.jump6);
        playerJump[6] = BitmapFactory.decodeResource(res, R.drawable.jump7);
        playerJump[7] = BitmapFactory.decodeResource(res, R.drawable.jump8);
        playerJump[8] = BitmapFactory.decodeResource(res, R.drawable.jump9);
        playerJump[9] = BitmapFactory.decodeResource(res, R.drawable.jump10);
        playerJump[10] = BitmapFactory.decodeResource(res, R.drawable.jump11);
        playerJump[11] = BitmapFactory.decodeResource(res, R.drawable.jump12);
        playerJump[12] = BitmapFactory.decodeResource(res, R.drawable.jump13);
        playerJump[13] = BitmapFactory.decodeResource(res, R.drawable.jump14);
        playerJump[14] = BitmapFactory.decodeResource(res, R.drawable.jump15);
        playerJump[15] = BitmapFactory.decodeResource(res, R.drawable.jump16);

        playerDead[0] = BitmapFactory.decodeResource(res, R.drawable.dead1);
        playerDead[1] = BitmapFactory.decodeResource(res, R.drawable.dead2);
        playerDead[2] = BitmapFactory.decodeResource(res, R.drawable.dead3);
        playerDead[3] = BitmapFactory.decodeResource(res, R.drawable.dead4);
        playerDead[4] = BitmapFactory.decodeResource(res, R.drawable.dead5);
        playerDead[5] = BitmapFactory.decodeResource(res, R.drawable.dead6);
        playerDead[6] = BitmapFactory.decodeResource(res, R.drawable.dead7);
        playerDead[7] = BitmapFactory.decodeResource(res, R.drawable.dead8);
        playerDead[8] = BitmapFactory.decodeResource(res, R.drawable.dead9);
        playerDead[9] = BitmapFactory.decodeResource(res, R.drawable.dead10);
        playerDead[10] = BitmapFactory.decodeResource(res, R.drawable.dead11);
        playerDead[11] = BitmapFactory.decodeResource(res, R.drawable.dead12);
        playerDead[12] = BitmapFactory.decodeResource(res, R.drawable.dead13);
        playerDead[13] = BitmapFactory.decodeResource(res, R.drawable.dead14);
        playerDead[14] = BitmapFactory.decodeResource(res, R.drawable.dead15);
        playerDead[15] = BitmapFactory.decodeResource(res, R.drawable.dead16);
        playerDead[16] = BitmapFactory.decodeResource(res, R.drawable.dead17);


        box = BitmapFactory.decodeResource(res, R.drawable.box);
        crystal = BitmapFactory.decodeResource(res, R.drawable.crystal);
        iceBox = BitmapFactory.decodeResource(res, R.drawable.iceBox);
        iceBox = BitmapFactory.decodeResource(res, R.drawable.iceBox);
        snowMan = BitmapFactory.decodeResource(res, R.drawable.snowMan);
        stone = BitmapFactory.decodeResource(res, R.drawable.stone);
        tapToStart = BitmapFactory.decodeResource(res, R.drawable.tapToStart);


    }

    public Bitmap getBackground() {
        return background;
    }

    public int getBackgroundWidth() {
        return background.getWidth();
    }

    public int getBackgroundHeight() {
        return background.getHeight();
    }

    public Bitmap getpath() {
        return path;
    }

    public int getpathWidth() {
        return path.getWidth();
    }

    public int getpathHeight() {
        return path.getHeight();
    }

    public Bitmap getPlayer(int pFrame) {
        return player[pFrame];
    }

    public int getplayerWidth() {
        return player[0].getWidth();
    }

    public int getplayerHeight() {
        return player[0].getHeight();
    }

    public Bitmap getPlayerJump(int pJFrame) {
        return playerJump[pJFrame];

    }

    public Bitmap getPlayerDead(int pDFrame) {
        return playerDead[pDFrame];
    }

    public int getPlayerDeadWidth() {
        return playerDead[0].getWidth();
    }

    public int getPlayerDeadHeight() {
        return playerDead[0].getHeight();
    }

    public Bitmap getTapToStart() {
        return tapToStart;
    }

    public int getTapToStartHeight() {
        return tapToStart.getHeight();
    }

    public int getTapToStartWidth() {
        return tapToStart.getWidth();
    }




}
