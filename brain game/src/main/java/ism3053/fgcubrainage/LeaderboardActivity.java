package ism3053.fgcubrainage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LeaderboardActivity extends AppCompatActivity {

//    RecyclerView recyclerView;
    private TextView textPlacement;
    private Button mainMenu_Activity_btn;

    // Create arrayList to store name, score, and gameMode from json file (NOT USED)
//    ArrayList<String> names = new ArrayList<>();
//    ArrayList<String> scores = new ArrayList<>();
//    ArrayList<String> gameModes = new ArrayList<>();

    // Arrays for every TextView (hard coded values)
    int [] names = new int[] {R.id.name0,R.id.name1, R.id.name2};
    int [] scores = new int[] {R.id.score0,R.id.score1, R.id.score2};
    int [] gameModes = new int[] {R.id.mode0,R.id.mode1, R.id.mode2};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        // Remove Action bar in the top
        getSupportActionBar().hide();

        mainMenu_Activity_btn = findViewById(R.id.menuReturn);

        // Event handler for the button to jump to the Main Menu when button is clicked
        mainMenu_Activity_btn.setOnClickListener(view -> {
            Intent intent = new Intent(LeaderboardActivity.this, MainMenuActivity.class);
            startActivity(intent);
            finish();
        });

        // Attempted to make Leaderboard dynamic but struggled with the utilization of RecyclerView
        // & Adapter (Never used them before)
//        recyclerView = findViewById(R.id.recyclerView);
//
//        // Configuration for RecyclerView
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Getting JSON data
        try{
            // Get JSON OBJECT
            JSONObject obj = new JSONObject(loadJSONfromAssets());

            // Fetch JSON array
            JSONArray userArray = obj.getJSONArray("users");

            // Loop to obtain the user list data
            for(int i = 0; i < userArray.length(); i++){

                // JSONObject for fetching specific data points
                JSONObject userDetail = userArray.getJSONObject(i);

//                // Store values into array for utilization in BoardAdapter (not used)
//                names.add(userDetail.getString("name"));
//                scores.add(userDetail.getString("score"));
//                gameModes.add(userDetail.getString("gameMode"));

                // Places name (hard coded values in JSON)
                textPlacement = findViewById(names[i]);
                textPlacement.setText(userDetail.getString("name"));

                // Places score (hard coded values in JSON)
                textPlacement = findViewById(scores[i]);
                textPlacement.setText(userDetail.getString("score"));

                // Places Mode (hard coded values in JSON)
                textPlacement = findViewById(gameModes[i]);
                textPlacement.setText(userDetail.getString("gameMode"));

            }

        }catch (JSONException e) {
            e.printStackTrace();
        }

//        BoardAdapter adapter = new BoardAdapter(names, scores, gameModes);
//        recyclerView.setAdapter(adapter);
    }

    // Load JSON from Assets
    private String loadJSONfromAssets() {

        String json = null;

        try{
            InputStream is = getAssets().open("data.json");
            int size = is.available();

            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            json = new String(buffer, "UTF-8");

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        // Return json string
        return json;
    }
}