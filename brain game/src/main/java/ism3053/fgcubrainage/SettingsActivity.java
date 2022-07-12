package ism3053.fgcubrainage;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

public class SettingsActivity extends AppCompatActivity {

    // ======== Not used in prototype but could be used in the future ========

    Button mainMenu_Activity_btn;
    Button aboutPage_Activity_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mainMenu_Activity_btn = findViewById(R.id.menuReturn);

        aboutPage_Activity_btn = findViewById(R.id.aboutButton);

        // Event handler for the button to jump to the Main Menu when button is clicked
        mainMenu_Activity_btn.setOnClickListener(view -> {
            Intent intent = new Intent(SettingsActivity.this, MainMenuActivity.class);
            startActivity(intent);
        });

        // Event handler for the button to jump to the About page when button is clicked
        aboutPage_Activity_btn.setOnClickListener(view -> {
            Intent intent = new Intent(SettingsActivity.this, AboutActivity.class);
            startActivity(intent);
        });
    }
}