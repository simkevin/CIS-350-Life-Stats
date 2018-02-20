package lifestats.a350s18_21_lifestats;

import android.hardware.fingerprint.FingerprintManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {
    private AmazonDynamoDBClient dynamoDBClient;
    private DynamoDBMapper dbMapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AWSMobileClient.getInstance().initialize(this).execute();
        dynamoDBClient = new AmazonDynamoDBClient(
                AWSMobileClient.getInstance().getCredentialsProvider());
        dbMapper = DynamoDBMapper.builder()
                .dynamoDBClient(dynamoDBClient).
                        awsConfiguration(AWSMobileClient.getInstance().getConfiguration()).build();
        setContentView(R.layout.activity_main);
    }
}
