package ism3053.fgcubrainage;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;

public class MainMenuActivity extends AppCompatActivity {

    ImageView logoView;
    Button start_Activity_btn;
    Button board_Activity_btn;
    Button about_Activity_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        // Find logoView and call rotateLogo
        logoView=findViewById(R.id.logo);
        rotateLogo(logoView);

        // Give component location to variable
        start_Activity_btn = findViewById(R.id.startButton);
        board_Activity_btn = findViewById(R.id.boardButton);
        about_Activity_btn = findViewById(R.id.settingButton);

        // Event handler for the button to jump to the Category Page when button is clicked
        start_Activity_btn.setOnClickListener(view -> {
            Intent intent = new Intent(MainMenuActivity.this, CategoryActivity.class);
            startActivity(intent);
        });

        // Event handler for the button to jump to the Category Page when button is clicked
        board_Activity_btn.setOnClickListener(view -> {
            Intent intent = new Intent(MainMenuActivity.this, LeaderboardActivity.class);
            startActivity(intent);
        });

        // Event handler for the button to jump to the About Page when button is clicked
        about_Activity_btn.setOnClickListener(view -> {
            Intent intent = new Intent(MainMenuActivity.this, AboutActivity.class);
            startActivity(intent);
        });


    }

    // Given specific Imageview it will rotate infinitely
    public void rotateLogo(ImageView logoView){
        RotateAnimation rotate = new RotateAnimation(0,360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(6000);
        rotate.setRepeatCount(Animation.INFINITE);
        rotate.setInterpolator(new LinearInterpolator());

        logoView.startAnimation(rotate);
    }
}