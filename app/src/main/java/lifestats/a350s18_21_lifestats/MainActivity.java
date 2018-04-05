package lifestats.a350s18_21_lifestats;

import android.hardware.fingerprint.FingerprintManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import android.content.Intent;
import android.view.View;
import com.amazonaws.auth.CognitoCredentialsProvider;

public class MainActivity extends AppCompatActivity {
    private AmazonDynamoDBClient dynamoDBClient;
    private DynamoDBMapper dbMapper;
    private CognitoCredentialsProvider credentials;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //AWSMobileClient.getInstance().initialize(this).execute();
        //dynamoDBClient = new AmazonDynamoDBClient(
        //        AWSMobileClient.getInstance().getCredentialsProvider());
        //dbMapper = DynamoDBMapper.builder()
        //        .dynamoDBClient(dynamoDBClient).
        //                awsConfiguration(AWSMobileClient.getInstance().getConfiguration()).build();
        /*
        credentials = (CognitoCredentialsProvider) AWSMobileClient.getInstance().getCredentialsProvider();
        setContentView(R.layout.activity_main);*/
    }

    public void logout(View v) {
        credentials.clear();
        Intent signInIntent = new Intent(MainActivity.this, AuthenticatorActivity.class);
        MainActivity.this.startActivity(signInIntent);
    }

}