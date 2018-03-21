package lifestats.a350s18_21_lifestats;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.amazonaws.auth.CognitoCredentialsProvider;
import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

public class ControlPanelActivity extends AppCompatActivity {

    private DynamoDBMapper dynamoDBMapper;
    private AmazonDynamoDBClient dynamoDBClient;
    private CognitoCachingCredentialsProvider credentialsProvider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // This initializes the dynamo db mapper

       this.credentialsProvider = new CognitoCachingCredentialsProvider(
                getApplicationContext(),
                "us-east-1:2ab9a7af-b70a-491b-8f1f-a773f3cf6557",
                Regions.US_EAST_1
        );

        AWSMobileClient.getInstance().setCredentialsProvider(credentialsProvider);
        this.dynamoDBClient = new AmazonDynamoDBClient(AWSMobileClient.getInstance().getCredentialsProvider());
        this.dynamoDBMapper = DynamoDBMapper.builder()
                .dynamoDBClient(dynamoDBClient)
                .awsConfiguration(AWSMobileClient.getInstance().getConfiguration())
                .build();
        setContentView(R.layout.activity_control_panel);
    }

    public void openLifeStats(View view) {
       // Intent intent = new Intent(this, LifeStatsActivity.class);
       // startActivity(intent);
    }

    public void openDataEntry(View view) {
        //Intent intent = new Intent(this, DataEntryActivity.class);
        //startActivity(intent);
    }

    public void openDataAnalysis(View view) {
        //Intent intent = new Intent(this, DataAnalysisActivity.class);
        //startActivity(intent);
    }

    /*
     * This is the method called when the goals button is called. It opens the GoalsMain
     * activity.
     */
    public void openGoals(View view) {
        Intent intent = new Intent(this, GoalsMain.class);
        startActivity(intent);
    }

    /*
     *This is the method called when the summary button is pressed. It opnes the
     * summary activity.
     */
    public void openSummary(View view) {
        Intent intent = new Intent(this, Summary.class);
        startActivity(intent);
    }

    public void openSleep(View view) {
        Intent intent = new Intent(this, SleepScreen.class);
        startActivity(intent);
    }

    /*
     * This is the method called when the about button is called. It takes the user to the
     * about page.
     */
    public void openAbout(View view) {
        Intent intent = new Intent(this, AboutActivity.class);
        startActivity(intent);
    }
}
