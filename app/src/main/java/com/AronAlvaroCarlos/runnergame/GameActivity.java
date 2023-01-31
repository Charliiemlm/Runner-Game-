package com.AronAlvaroCarlos.runnergame;

import android.os.Bundle;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {
    GameView gameView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //esta clase en principio es para los anuncios , probar que pasa si se borra
        gameView= new Gameview(this);


    }
}
