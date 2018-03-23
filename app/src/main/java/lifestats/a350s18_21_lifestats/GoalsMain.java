package lifestats.a350s18_21_lifestats;

import android.content.Intent;
import android.media.Rating;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;

import java.util.ArrayList;
import java.util.HashMap;

public class GoalsMain extends AppCompatActivity {


    private static HashMap <String, String> goalsToDifficulty = new HashMap<String, String>(); //This should be a database??
    private DynamoDBMapper dynamoDBMapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals_main);

        Button submitButton = (Button) findViewById(R.id.goalButton);
        final Button pastGoals = (Button) findViewById(R.id.pastGoals);
        final EditText goalText = (EditText) findViewById(R.id.goalText);
        final RatingBar difficultyBar = (RatingBar) findViewById(R.id.difficultyRating);

        // This initializes the dynamo db mapper
        AmazonDynamoDBClient dynamoDBClient = new AmazonDynamoDBClient(AWSMobileClient.getInstance().getCredentialsProvider());
        this.dynamoDBMapper = DynamoDBMapper.builder()
                .dynamoDBClient(dynamoDBClient)
                .awsConfiguration(AWSMobileClient.getInstance().getConfiguration())
                .build();
 

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
    }
}