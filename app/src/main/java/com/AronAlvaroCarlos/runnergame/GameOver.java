package com.AronAlvaroCarlos.runnergame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;

public class GameOver extends AppCompatActivity {

    TextView tvPoints;
    ImageView ivNewHighest;

    private Context context;
    private MediaPlayer musica_fondo_over;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        //ivNewHighest = findViewById(R.id.ivNewHeighest);
        int points = getIntent().getExtras().getInt("points");
        tvPoints = findViewById(R.id.tvPoints);
        tvPoints.setText("" + points);
        musica_fondo_over=MediaPlayer.create(getApplicationContext(),R.raw.final_screen);
        musica_fondo_over.start();

        /* if (points == 240) {
            ivNewHighest.setVisibility(View.VISIBLE);
        }*/
    }

    public void restart(View view) {
        Intent intent = new Intent(GameOver.this, Juego.class);
        startActivity(intent);
        musica_fondo_over.stop();
        finish();
    }

    public void exit(View view) {
        musica_fondo_over.stop();
        finish();
    }
}

