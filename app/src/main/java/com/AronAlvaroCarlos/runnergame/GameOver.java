package com.AronAlvaroCarlos.runnergame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class GameOver extends AppCompatActivity {

    TextView tvScore, tvPersonalBest;
    private AdView mAdView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        mAdView = findViewById(R.id.adViewGameOver);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        InterstitialAd.load(this, getResources().getString(R.string.interstitial_adunit_id), adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The interstitialAd reference will be null until
                        // an ad is loaded.
                        interstitialAd.show(GameOver.this);
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        // Log.i(TAG, loadAdError.getMessage());
                    }
                });
        int points = getIntent().getExtras().getInt("points");
        SharedPreferences pref = getSharedPreferences("MyPref", 0);
        int pointsSP = pref.getInt("pointsSP", 0);
        SharedPreferences.Editor editor = pref.edit();
        if (points > pointsSP) {
            pointsSP = points;
            editor.putInt("pointsSP", pointsSP);
            editor.commit();
        }
        tvScore = findViewById(R.id.tvPoints);
        tvPersonalBest = findViewById(R.id.tvPersonalBest);
        tvScore.setText("" + points);
        tvPersonalBest.setText("" + pointsSP);
    }

    public void restart(View view) {
        Intent intent = new Intent(GameOver.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void exit(View view) {
        finish();
    }
}
