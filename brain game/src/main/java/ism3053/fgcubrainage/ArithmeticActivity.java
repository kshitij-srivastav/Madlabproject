package ism3053.fgcubrainage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class ArithmeticActivity extends AppCompatActivity {

    private TextView questionPrompt;
    private Button gridButtons;
    private boolean buttonStates = false;
    private ImageView Lives;
    private TextView displayScore;

    // Bug found with testing: Sometimes questions may have duplicate answers due to order
    // of value creation in logic. Could update to have correct answer be placed first
    // and set all other values according to that position.

    // ============= Game variables to be utilized =============
    // Bounds for random numbers range equivalence [1,9] || [10,99] || [100,999] || [1000,9999]
    private int max = 50;
    private int min = 0;

    // Set char array with arithmetic symbols
    char[] arithmeticSymbols = new char[] {'+','-','*','/'};

    // Int array with every Id of grid buttons
    int [] buttonId = new int[] {R.id.gridButton0,R.id.gridButton1, R.id.gridButton2, R.id.gridButton3};

    // Keep track of correct answer to validate form onClick
    private int correctAnswer;
    private double correctAnswerDiv;

    // Count to keep track of # of errors. If less than 0 (meaning >= 3) end game
    private int numberOfLives = 2;

    // Difficult Increase Counter
    private int difCount = 0;
    private int currentScore = 0;
    // ============= Game variables to be utilized =============

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arithmetic);

        questionPrompt = findViewById(R.id.questionText);
        displayScore = findViewById(R.id.scoreCount);

        // Call main logic of Arithmetic game
        ArithmeticGame();
    }


    public void onMemButtonClick(View view) {
        // Obtains predefined button value
        Button incomingValue = (Button)view;

        // Set selected value into string
        String userSelection = incomingValue.getText().toString();

        // Disables buttons click to avoid spam clicks
        setButtonState(buttonStates);

        // Check answer that was selected
        validateAnswer(userSelection);
    }

    public void ArithmeticGame(){
        // End game is # of Lives is < 0
        if(numberOfLives < 0){
            gameOver();
        }
        else{
            // Set handler for postDelay
            Handler handler = new Handler();

            // Displays currentScore
            displayScore.setText(Integer.toString(currentScore));

            // Checks difficulty state;
            checkDifficulty();

            // Disables buttons on Load
            setButtonState(buttonStates);

            // Display question
            generateQuestion();

            // Remove provided digits & enable buttons for user to guess
            handler.postDelayed(() -> {
                setButtonState(!buttonStates);
            }, 2000);
        }
    }

    // Generates questions
    public void generateQuestion(){

        Random digit = new Random();

        // Generate correct answer position
        int generatePosition = digit.nextInt(4);

        // Generate value for array symbol
        int selectSymbol = digit.nextInt(4);

        String firstValue = Integer.toString(digit.nextInt(max)+min);
        String secondValue = Integer.toString(digit.nextInt(max)+min);

        questionPrompt.setText("What is " + firstValue + " " + arithmeticSymbols[selectSymbol] +" " + secondValue);

        // Call valuePlacements to place values within buttons
        valuePlacements(firstValue, secondValue, generatePosition, selectSymbol);
    }

    // Takes in specific questionParameters and sets buttons according to position
    public void valuePlacements(String firstValue, String secondValue, int generatePosition, int selectSymbol){

        Random digit = new Random();

        // Loop for placing values on buttons (not a fan on big if-else blocks but gets the job done)
        for(int i = 0; i < 4; i++){

            gridButtons = findViewById(buttonId[i]);
            // Check if gridButton position matches (if so place correct answer within this button)
            if(i == generatePosition){
                // Check if its addition
                if(arithmeticSymbols[selectSymbol] == '+'){
                    correctAnswer = Integer.parseInt(firstValue) + Integer.parseInt(secondValue);
                    gridButtons.setText(Integer.toString(correctAnswer));
                }
                // Check if its subtraction
                else if(arithmeticSymbols[selectSymbol] == '-'){
                    correctAnswer = Integer.parseInt(firstValue) - Integer.parseInt(secondValue);
                    gridButtons.setText(Integer.toString(correctAnswer));
                }
                // Check if multiplication
                else if(arithmeticSymbols[selectSymbol] == '*'){
                    correctAnswer = Integer.parseInt(firstValue) * Integer.parseInt(secondValue);
                    gridButtons.setText(Integer.toString(correctAnswer));
                }
                // Else its division
                else{
                    correctAnswerDiv = Double.parseDouble(firstValue) / Double.parseDouble(secondValue);
                    gridButtons.setText(String.format("%.2f", correctAnswerDiv));
                }


            }
            // Else place random value into buttons (if one matches roll again)
            else{
                // Generate random values
                String randomFirst = Integer.toString(digit.nextInt(max)+min);
                String randomSecond = Integer.toString(digit.nextInt(max)+min);

                int randomAnswer;
                double randomAnswerDiv;

                // Check if its addition
                if(arithmeticSymbols[selectSymbol] == '+'){
                    randomAnswer = Integer.parseInt(randomFirst) + Integer.parseInt(randomSecond);

                    // While loop to avoid duplicates answer
                    while(randomAnswer == correctAnswer){
                        // Generate random values
                        randomFirst = Integer.toString(digit.nextInt(max)+min);
                        randomSecond = Integer.toString(digit.nextInt(max)+min);

                        randomAnswer = Integer.parseInt(randomFirst) + Integer.parseInt(randomSecond);
                    }
                    gridButtons.setText(Integer.toString(randomAnswer));
                }
                // Check if its subtraction
                else if(arithmeticSymbols[selectSymbol] == '-'){
                    randomAnswer = Integer.parseInt(randomFirst) - Integer.parseInt(randomSecond);

                    // While loop to avoid duplicates answer
                    while(randomAnswer == correctAnswer){
                        // Generate random values
                        randomFirst = Integer.toString(digit.nextInt(max)+min);
                        randomSecond = Integer.toString(digit.nextInt(max)+min);

                        randomAnswer = Integer.parseInt(randomFirst) + Integer.parseInt(randomSecond);
                    }

                    gridButtons.setText(Integer.toString(randomAnswer));
                }
                // Check if multiplication
                else if(arithmeticSymbols[selectSymbol] == '*'){
                    randomAnswer = Integer.parseInt(randomFirst) * Integer.parseInt(randomSecond);

                    // While loop to avoid duplicates answer
                    while(randomAnswer == correctAnswer){
                        // Generate random values
                        randomFirst = Integer.toString(digit.nextInt(max)+min);
                        randomSecond = Integer.toString(digit.nextInt(max)+min);

                        randomAnswer = Integer.parseInt(randomFirst) + Integer.parseInt(randomSecond);
                    }

                    gridButtons.setText(Integer.toString(randomAnswer));
                }
                // Else its division
                else{
                    randomAnswerDiv = Double.parseDouble(randomFirst) / Double.parseDouble(randomSecond);

                    // While loop to avoid duplicates answer
                    while(randomAnswerDiv == correctAnswerDiv){
                        // Generate random values
                        randomFirst = Integer.toString(digit.nextInt(max)+min);
                        randomSecond = Integer.toString(digit.nextInt(max)+min);

                        randomAnswer = Integer.parseInt(randomFirst) + Integer.parseInt(randomSecond);
                    }

                    gridButtons.setText(String.format("%.2f", randomAnswerDiv));
                }
            }
        }
    }

    public void validateAnswer(String userSelection) {

        // If user is Correct up difficulty count & increment score
        if(userSelection.equals(Integer.toString(correctAnswer)) || userSelection.equals(String.format("%.2f", correctAnswerDiv))){
            String greetingText = getString(R.string.correct);
            showToast(greetingText);
            difCount++;
            currentScore = currentScore + 10;
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
            ArithmeticGame();
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
        questionPrompt.setText("Game Over!");

        // Set handler for postDelay
        Handler handler = new Handler();

        // Delay to ensure user saw Game Over then go to results
        handler.postDelayed(() -> {
            Intent intent = new Intent(ArithmeticActivity.this, ResultActivity.class);
            Bundle extras = new Bundle();
            // Pass Score and digit length (difficulty) reached
            extras.putInt("score", currentScore);
            intent.putExtras(extras);
            startActivity(intent);
            finish();
        }, 4000);
    }

    public void setButtonState(boolean buttonState) {

        // Int array with every Id of grid buttons
        int [] ids = new int[] {R.id.gridButton0,R.id.gridButton1, R.id.gridButton2, R.id.gridButton3};

        for(int i = 0; i < 4; i++){
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

    // Check if the user has any Lives left or if the difficulty needs to be increased
    public void checkDifficulty() {

        if(difCount == 2){

            // Increase the random number range to a digit above
            max = max + 50;

            // Reset difficulty counter for another potential increase
            difCount = 0;
        }
    }
}