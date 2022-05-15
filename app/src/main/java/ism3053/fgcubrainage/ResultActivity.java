package ism3053.fgcubrainage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity {

    // Implement asking userName & disable return button until the user Enters something.
    // Figure out Database aspect of Leaderboard and set it up.

    private TextView displayScore;
    private TextView displayDigit;
    private TextView difficultyText;
    private EditText userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // Pull passed variables from Intent
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        int score = extras.getInt("score");
        int digitLength = extras.getInt("digitLength");

        // Identify Specific Views
        displayScore = findViewById(R.id.scoreCount);
        displayDigit = findViewById(R.id.digitCount);
        difficultyText = findViewById(R.id.difText);
        userName = findViewById(R.id.nameText);

        // If digitLength is 0 the score is coming from Arithmetic
        if(digitLength == 0){
            displayScore.setText(Integer.toString(score));
            difficultyText.setVisibility(View.GONE);
        }
        // Score coming from Memory
        else{
            // Set score & dif
            displayScore.setText(Integer.toString(score));
            displayDigit.setText(Integer.toString(digitLength));
        }

    }

    public void onReturnClick(View view) {

        String text = userName.getText().toString();

        // If empty prompt them Toast to enter something. Else delay submit score to DB and return to page
        if(text.isEmpty()){
            showToast("Please Enter a Name!");
        }
        else{
            showToast(text + " was added to the Leaderboard");

            // Set handler for postDelay
            Handler handler = new Handler();

            // Delay to validate some stateChanges then continue game
            handler.postDelayed(() -> {
                // Return to Category Selection
                Intent intent = new Intent(ResultActivity.this, CategoryActivity.class);
                startActivity(intent);
                finish();
            }, 3000);
        }
    }

    public void showToast(String greetingText) {
        Toast prompt = Toast.makeText(this, greetingText, Toast.LENGTH_LONG);
        prompt.setGravity(Gravity.CENTER, 0,550);
        prompt.show();
    }
}