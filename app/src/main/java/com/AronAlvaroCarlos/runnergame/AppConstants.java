package com.AronAlvaroCarlos.runnergame;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public class AppConstants {

    static Bitmap bitmapBank;
    static GameEngine gameEngine;
    static int SCREEN_WIDTH, SCREEN_HEIGHT;
    static int gravity;
    static int VELOCITY_WHEN_JUMPED;
    static  int VELOCITY_OBSTACLES;
    static Context gameActivityContext;
    static boolean playerGrounded;


    //recibe el context desde el mainactivity

    public static  void  initialization(Context context){
        setScreenSize(context);
        AppConstants.gameActivityContext = context;
        bitmapBank = new BitmapBank(context.getResources());
        setGameConstants();
        gameEngine= new GameEngine();


    }
    public  static void setScreenSize(Context context){
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        int width= metrics.widthPixels;
        int height = metrics.heightPixels;
        AppConstants.SCREEN_HEIGHT=height;
        AppConstants.SCREEN_WIDTH=width;

    }

    public static void setGameConstants(){
        AppConstants.gravity=3;
        AppConstants.VELOCITY_OBSTACLES=45;
        AppConstants.VELOCITY_WHEN_JUMPED=-40;
        AppConstants.playerGrounded= true;

    }

    public static BitmapBank getBitmapBank(){
        return bitmapBank;
    }
    public static GameEngine getGameEngine(){
        return gameEngine;

    }
}
