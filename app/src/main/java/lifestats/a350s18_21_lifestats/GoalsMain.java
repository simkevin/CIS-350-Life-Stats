package lifestats.a350s18_21_lifestats;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import java.util.ArrayList;

// this class is the screen from where goals are managed
public class GoalsMain extends AppCompatActivity {
    private static GoalsToDifficultyWrapper goalsToDifficulty =
            GoalsToDifficultyWrapper.getInstance();
    private static GoalToDeadlineWrapper goalsToDeadline =
            GoalToDeadlineWrapper.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals_main);

        Button submitButton = findViewById(R.id.goalButton);
        final Button pastGoals = findViewById(R.id.pastGoals);
        final Button chartGenerator = findViewById(R.id.chartButton);

        final EditText goalText = findViewById(R.id.goalText);
        final RatingBar difficultyBar = findViewById(R.id.difficultyRating);
        final EditText goalNumberEditText = findViewById(R.id.goalNumberEditText);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //only enter a goal that actually has letters in it
                if (goalText.getText().toString().matches(".*\\w.*")) {
                    String goal = goalText.getText().toString();
                    String rate = String.valueOf(difficultyBar.getRating());
                    Double days = Double.parseDouble(goalNumberEditText.getText().toString());
                    goalsToDifficulty.put(goal, rate);
                    goalsToDeadline.put(goal, days);
                }

                goalText.setText("");
                goalNumberEditText.setText("");
                difficultyBar.setRating(0F);
            }
        });

        pastGoals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String>  values = new ArrayList<String>();
                ArrayList<String>  ratings = new ArrayList<String>();
                for (String s : goalsToDifficulty.keySet()) {
                    String difficulty = goalsToDifficulty.get(s).split("&")[0];
                    Double numDaysLeft = goalsToDeadline.get(s);


                    if (goalsToDifficulty.get(s).split("&")[2].equals("0")) {
                        values.add(s + ": \t\tDifficulty: " + difficulty + "\t\tNo. Days left: "
                                + numDaysLeft);
                        ratings.add(difficulty);
                    }
                }
                Intent pastScreen = new Intent(GoalsMain.this, PastGoals.class);
                pastScreen.putExtra("goalList", values);
                pastScreen.putExtra("ratingList", ratings);
                startActivity(pastScreen);
            }
        });

        chartGenerator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent chartScreen = new Intent(GoalsMain.this, Charts.class);
                ArrayList<String>  goals = new ArrayList<String>();
                ArrayList<String>  ratings = new ArrayList<String>();
                for (String s : goalsToDifficulty.keySet()) {
                    if (goalsToDifficulty.get(s).split("&")[2].equals("0")) {
                        goals.add(s);
                        ratings.add(goalsToDifficulty.get(s).split("&")[0]);
                    }
                }
                chartScreen.putExtra("goalList", goals);
                chartScreen.putExtra("ratingList", ratings);
                startActivity(chartScreen);
            }
        });


    }

}
