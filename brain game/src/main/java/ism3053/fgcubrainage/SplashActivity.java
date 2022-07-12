package ism3053.fgcubrainage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Set handler for postDelay
        Handler handler = new Handler();

        // Remove Action bar in the top
        getSupportActionBar().hide();

        // When started start 3 (3000) seconds delay from Splash to Home page
        handler.postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, MainMenuActivity.class);
            startActivity(intent);
            finish();
        }, 3000);
    }
}