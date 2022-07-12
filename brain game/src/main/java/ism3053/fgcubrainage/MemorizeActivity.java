package ism3053.fgcubrainage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MemorizeActivity extends AppCompatActivity {

    private GridLayout memoryGrid;
    private TextView memResponse;
    private boolean buttonStates = false;
    private Button gridButtons;
    private TextView displayScore;
    private ImageView Lives;

    // ============= Game variables to be utilized =============
    private String userEntry;

    // Provides initial length value to be incremented for different random ranges
    private int digitLengthCount = 2;

    // Bounds for random numbers range equivalence [10,99] || [100,999] || [1000,9999]
    private int max = 89;
    private int min = 10;
    private String generatedNumber;

    // Count to keep track of # of errors. If less than 0 (meaning >= 3) end game
    private int numberOfLives = 2;

    // Difficult Increase Counter
    private int difCount = 0;
    private int currentScore = 0;
    // ============= Game variables to be utilized =============

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memorize);

        // Initializes variables
        memoryGrid = findViewById(R.id.mem_grid);
        memResponse = findViewById(R.id.textResponse);
        displayScore = findViewById(R.id.scoreCount);

        // Call game start
        MemoryGame();
    }

    public void onButtonClick(View view) {

        // Obtains predefined button value
        Button incomingValue = (Button)view;
        // If first instance set initial value, else concatenate
        if(userEntry == null || userEntry == ""){
            userEntry = incomingValue.getText().toString();
        }
        else{
            userEntry = userEntry + incomingValue.getText().toString();
        }

        if(userEntry.length() == digitLengthCount){
            memResponse.setText(userEntry);
            validateAnswer(userEntry);
        }
        else{
            memResponse.setText(userEntry);
        }
    }

    public void MemoryGame(){

        Random digit = new Random();

        // Set handler for postDelay
        Handler handler = new Handler();

        // Check if user has any Lives left (if not end game)
        if(numberOfLives < 0){
            gameOver();
        }
        else{
            // Checks difficulty state;
            checkDifficulty();

            // Displays currentScore
            displayScore.setText(Integer.toString(currentScore));

            // Random number generator as questions (with bound respect to digitLengthCount
            generatedNumber = Integer.toString(digit.nextInt(max)+min);

            // Disables buttons on Load
            setButtonState(buttonStates);

            // Display length & populate digit
            memResponse.setText(digitLengthCount + " digits");

            // Change provided text to randomly generated value
            handler.postDelayed(() -> {
                memResponse.setText(generatedNumber);
                userEntry = "";
            }, 2000);

            // Remove provided digits & enable buttons for user to guess
            handler.postDelayed(() -> {
                memResponse.setText("");
                setButtonState(!buttonStates);
            }, 3500);
        }

    }

    public void setButtonState(boolean buttonState) {

        // Int array with every Id of grid buttons
        int [] ids = new int[] {R.id.gridButton0,R.id.gridButton1, R.id.gridButton2, R.id.gridButton3, R.id.gridButton4, R.id.gridButton5, R.id.gridButton6, R.id.gridButton7, R.id.gridButton8, R.id.gridButton9};

        for(int i = 0; i < 10; i++){
            gridButtons = findViewById(ids[i]);
            if(buttonState == true){
                gridButtons.setAlpha(1f);
            }
            else{
                gridButtons.setAlpha(.5f);
            }
            gridButtons.setClickable(buttonState);
        }
    }

    public void validateAnswer(String userEntry) {

        // If user is Correct up difficulty count & increment score
        if(userEntry.equals(generatedNumber)){
            String greetingText = getString(R.string.correct);
            showToast(greetingText);
            difCount++;
            currentScore = currentScore + 5;
        }
        // Else remove Live & continue
        else{
            String greetingText = getString(R.string.incorrect);
            showToast(greetingText);
            removeLive(numberOfLives);
            numberOfLives--;
        }

        // Set handler for postDelay
        Handler handler = new Handler();

        // Delay to validate some stateChanges then continue game
        handler.postDelayed(() -> {
            MemoryGame();
        }, 3000);
    }

    public void showToast(String greetingText) {
        Toast prompt = Toast.makeText(this, greetingText, Toast.LENGTH_LONG);
        prompt.setGravity(Gravity.CENTER, 0,550);
        prompt.show();
    }

    public void removeLive(int index) {
        // Int array with every Id of Lives
        int [] ids = new int[] {R.id.Live0,R.id.Live1, R.id.Live2};

        // sets ImageView to specific index & makes it disappear
        Lives = findViewById(ids[index]);
        Lives.setVisibility(View.GONE);
    }

    // Check if the user has any Lives left or if the difficulty needs to be increased
    public void gameOver() {

        // Disable buttons
        setButtonState(buttonStates);

        // Display Game Over
        memResponse.setText("Game Over!");

        // Set handler for postDelay
        Handler handler = new Handler();

        // Delay to ensure user saw Game Over then go to results
        handler.postDelayed(() -> {
            Intent intent = new Intent(MemorizeActivity.this, ResultActivity.class);
            Bundle extras = new Bundle();
            // Pass Score and digit length (difficulty) reached
            extras.putInt("score", currentScore);
            extras.putInt("digitLength", digitLengthCount);
            intent.putExtras(extras);
            startActivity(intent);
            finish();
        }, 4000);
    }

    // Check if the user has any Lives left or if the difficulty needs to be increased
    public void checkDifficulty() {

        if(difCount == 2){
            // Temp variables being attached to max or min
            int nine =  9;
            int zero = 0;

            // Increase the random number range to a digit above
            max = Integer.valueOf(String.valueOf(max) + String.valueOf(nine));
            min = Integer.valueOf(String.valueOf(min) + String.valueOf(zero));

            // Increment length for digit above
            digitLengthCount++;

            // Reset difficulty counter for another potential increase
            difCount = 0;
        }
    }


}