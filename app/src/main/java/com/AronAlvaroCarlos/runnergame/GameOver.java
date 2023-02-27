package com.AronAlvaroCarlos.runnergame;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class GameOver extends AppCompatActivity {

    private TextView tvPoints;
     private ImageView imageRestart;
    private ImageView imageApagar;

     private MediaPlayer musica_fondo_over;

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
         imageRestart = findViewById(R.id.imageRestart);
        imageApagar= findViewById(R.id.imageApagar);

        int points = getIntent().getExtras().getInt("points");
        tvPoints = findViewById(R.id.tvPoints);
        tvPoints.setText( points +"");
        musica_fondo_over=MediaPlayer.create(getApplicationContext(),R.raw.final_screen);
        musica_fondo_over.start();



        imageRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GameOver.this, Juego.class);
                startActivity(intent);
                musica_fondo_over.stop();
                finish();
            }
        });
        imageApagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                musica_fondo_over.stop();
                finish();
            }
        });
    }






}

