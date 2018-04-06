package lifestats.a350s18_21_lifestats;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import java.util.Map;
import java.util.HashMap;
import com.facebook.AccessToken;
import com.facebook.login.LoginManager;

// this class acts as the main hub, providing the user many options for what to do
public class ControlPanelActivity extends AppCompatActivity {

    private DynamoDBMapper dynamoDBMapper;
    private AmazonDynamoDBClient dynamoDBClient;
    private CognitoCachingCredentialsProvider credentialsProvider;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // this creates the cognito credential provider
        this.credentialsProvider = new CognitoCachingCredentialsProvider(
                getApplicationContext(),
                "us-east-1:2ab9a7af-b70a-491b-8f1f-a773f3cf6557",
                Regions.US_EAST_1
        );

        Map<String, String> logins = new HashMap<String, String>();
        logins.put("graph.facebook.com", AccessToken.getCurrentAccessToken().getToken());
        credentialsProvider.setLogins(logins);


        // Async thread to get the credentials from amazon
        new Thread(new Runnable() {
            @Override
            public void run() {
                credentialsProvider.refresh();
            }
        }).start();

        AWSMobileClient.getInstance().setCredentialsProvider(credentialsProvider);
        this.dynamoDBClient = new AmazonDynamoDBClient(AWSMobileClient.getInstance().getCredentialsProvider());
        this.dynamoDBMapper = DynamoDBMapper.builder()
                .dynamoDBClient(dynamoDBClient)
                .awsConfiguration(AWSMobileClient.getInstance().getConfiguration())
                .build();
        initializeDataBase();
        setContentView(R.layout.activity_control_panel);

    }

    public void logout(View view) {
        LoginManager.getInstance().logOut();
        Intent intent = new Intent(this, AuthenticatorActivity.class);
        startActivity(intent);
    }

    public void openLifeStats(View view) {
        Intent intent = new Intent(this, LifeStats.class);
        startActivity(intent);
    }

    public void openDataEntry(View view) {
        Intent intent = new Intent(this, QuantitativeDataEntry.class);
        startActivity(intent);
    }

    public void openMoney(View view) {
        Intent intent = new Intent(this, MoneySpent.class);
        startActivity(intent);
    }

    public void openJournal(View view) {
        Intent intent = new Intent(this, Journal.class);
        startActivity(intent);
    }

    public void openRecords(View view) {
        Intent intent = new Intent(this, RecordsActivity.class);
        startActivity(intent);
    }

    public void openDataAnalysis(View view) {
        Intent intent = new Intent(this, DataAnalysisActivity.class);
        startActivity(intent);
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

    /*
     * This method calls the single constructors which fetches the data from the database
     * premptively so that it is ready to use.
     */
    private static void initializeDataBase() {
        GoalsToDifficultyWrapper.getInstance();
        DateToMoneyWrapper.getInstance();
        HappinessWrapper.getInstance();
        StressWrapper.getInstance();
        ProductivityWrapper.getInstance();
    }
}
