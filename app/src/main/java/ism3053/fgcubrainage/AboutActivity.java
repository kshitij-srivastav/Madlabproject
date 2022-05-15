package ism3053.fgcubrainage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class AboutActivity extends AppCompatActivity {

    Button mainMenu_Activity_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        // Remove Action bar in the top
        getSupportActionBar().hide();

        mainMenu_Activity_btn = findViewById(R.id.settingsReturn);

        // Event handler for the button to jump to the Main Menu page when button is clicked
        mainMenu_Activity_btn.setOnClickListener(view -> {
            Intent intent = new Intent(AboutActivity.this, MainMenuActivity.class);
            startActivity(intent);
            finish();
        });
    }
}