package ism3053.fgcubrainage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;

public class CategoryActivity extends AppCompatActivity {

    ImageView additionSymbol;
    ImageView subtractionSymbol;
    ImageView multiplicationSymbol;
    ImageView divisionSymbol;
    Button memorize_Activity_btn;
    Button mainMenu_Activity_btn;
    Button arith_Activity_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        // Give component location to variable
        memorize_Activity_btn = findViewById(R.id.memoryBtn);
        mainMenu_Activity_btn = findViewById(R.id.menuReturn);
        arith_Activity_btn = findViewById(R.id.arithBtn);

        // Find additionSymbol and call rotateAdd
        additionSymbol=findViewById(R.id.additionSym);
        rotateAdd(additionSymbol);

        // Find subtractionSymbol and call rotateSub
        subtractionSymbol=findViewById(R.id.subSym);
        rotateSub(subtractionSymbol);

        // Find multiplicationSymbol and call rotateMult
        multiplicationSymbol=findViewById(R.id.multSym);
        rotateMult(multiplicationSymbol);

        // Find divisionSymbol and call rotateDiv
        divisionSymbol=findViewById(R.id.divSym);
        rotateDiv(divisionSymbol);

        // Event handler for the button to jump to the Arithmetic Game when button is clicked
        arith_Activity_btn.setOnClickListener(view -> {
            Intent intent = new Intent(CategoryActivity.this, ArithmeticActivity.class);
            startActivity(intent);
        });

        // Event handler for the button to jump to the Memory Game when button is clicked
        memorize_Activity_btn.setOnClickListener(view -> {
            Intent intent = new Intent(CategoryActivity.this, MemorizeActivity.class);
            startActivity(intent);
        });

        // Event handler for the button to jump to the Main Menu when button is clicked
        mainMenu_Activity_btn.setOnClickListener(view -> {
            Intent intent = new Intent(CategoryActivity.this, MainMenuActivity.class);
            startActivity(intent);
            finish();
        });
    }


    // Given specific Imageview it will rotate infinitely
    public void rotateAdd(ImageView additionSymbol){
        RotateAnimation rotate = new RotateAnimation(0,360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 5.5f);
        rotate.setDuration(3000);
        rotate.setRepeatCount(Animation.INFINITE);
        rotate.setInterpolator(new LinearInterpolator());

        additionSymbol.startAnimation(rotate);
    }

    // Given specific Imageview it will rotate infinitely
    public void rotateSub(ImageView subtractionSymbol){
        RotateAnimation rotate = new RotateAnimation(0,360, Animation.RELATIVE_TO_SELF, 0.4f, Animation.RELATIVE_TO_SELF, 25f);
        rotate.setDuration(5000);
        rotate.setRepeatCount(Animation.INFINITE);
        rotate.setInterpolator(new LinearInterpolator());

        subtractionSymbol.startAnimation(rotate);
    }

    // Given specific Imageview it will rotate infinitely
    public void rotateMult(ImageView multiplicationSymbol){
        RotateAnimation rotate = new RotateAnimation(0,360, Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, -6.5f);
        rotate.setDuration(7000);
        rotate.setRepeatCount(Animation.INFINITE);
        rotate.setInterpolator(new LinearInterpolator());

        multiplicationSymbol.startAnimation(rotate);
    }

    // Given specific Imageview it will rotate infinitely
    public void rotateDiv(ImageView divisionSymbol){
        RotateAnimation rotate = new RotateAnimation(0,360, Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, -8f);
        rotate.setDuration(2000);
        rotate.setRepeatCount(Animation.INFINITE);
        rotate.setInterpolator(new LinearInterpolator());

        divisionSymbol.startAnimation(rotate);
    }
}