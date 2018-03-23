package lifestats.a350s18_21_lifestats;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import java.util.ArrayList;
import java.util.HashMap;

public class GoalsMain extends AppCompatActivity {
    private static GoalsToDifficultyWrapper goalsToDifficulty =
            new lifestats.a350s18_21_lifestats.GoalsToDifficultyWrapper();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals_main);

        Button submitButton = findViewById(R.id.goalButton);
        final Button pastGoals = findViewById(R.id.pastGoals);
        final Button chartGenerator = findViewById(R.id.chartButton);

        final EditText goalText = findViewById(R.id.goalText);
        final RatingBar difficultyBar = findViewById(R.id.difficultyRating);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //only enter a goal that actually has letters in it
                if (goalText.getText().toString().matches(".*\\w.*")) {
                    String goal = goalText.getText().toString();
                    String rate = String.valueOf(difficultyBar.getRating());
                    goalsToDifficulty.put(goal, rate);
                }

                goalText.setText("");
                difficultyBar.setRating(0F);
            }
        });

        pastGoals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String>  values = new ArrayList<String>();
                ArrayList<String>  ratings = new ArrayList<String>();
                for (String s : goalsToDifficulty.keySet()) {
                    values.add(s + ", \t\t\t\tDifficulty: " + goalsToDifficulty.get(s));
                    ratings.add(goalsToDifficulty.get(s));
                }
                Intent pastScreen = new Intent(GoalsMain.this, pastGoals.class);
                pastScreen.putExtra("goalList", values);
                pastScreen.putExtra("ratingList", ratings);
                startActivity(pastScreen);
            }
        });

        chartGenerator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent chartScreen = new Intent(GoalsMain.this, charts.class);
                ArrayList<String>  goals = new ArrayList<String>();
                ArrayList<String>  ratings = new ArrayList<String>();
                for (String s : goalsToDifficulty.keySet()) {
                    goals.add(s);
                    ratings.add(goalsToDifficulty.get(s));
                }
                chartScreen.putExtra("goalList", goals);
                chartScreen.putExtra("ratingList", ratings);
                startActivity(chartScreen);
            }
        });


    }

}
