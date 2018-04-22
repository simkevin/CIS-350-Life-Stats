package lifestats.a350s18_21_lifestats;
import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


/**
 * Created by steph on 3/23/2018.
 */

public class HappinessThresholdWrapper {
    private double threshold = 0;
    private DynamoDBMapper dynamoDBMapper;
    private AmazonDynamoDBClient dynamoDBClient;
    private static HappinessThresholdWrapper thisInstance;


    public static HappinessThresholdWrapper getInstance() {
        if (thisInstance == null) {
            thisInstance = new HappinessThresholdWrapper();
        }
        return thisInstance;
    }

    private HappinessThresholdWrapper () {
        this.dynamoDBClient = new AmazonDynamoDBClient(AWSMobileClient.getInstance().getCredentialsProvider());
        this.dynamoDBMapper = DynamoDBMapper.builder()
                .dynamoDBClient(dynamoDBClient)
                .awsConfiguration(AWSMobileClient.getInstance().getConfiguration())
                .build();
        getDataBase();
    }


    public void setThreshold(double threshold) {
        this.threshold = threshold;
        updateDataBase();
    }

    public double getThreshold() {
        return threshold;
    }

    private void getDataBase() {

        new Thread(new Runnable() {
            @Override
            public void run() {

                // We set the userID, as it is the key
                CognitoCachingCredentialsProvider provider =
                        (CognitoCachingCredentialsProvider) AWSMobileClient.getInstance().getCredentialsProvider();
                HappinessThresholdDO happinessThresholdDO = dynamoDBMapper.load(
                        HappinessThresholdDO.class,
                        provider.getIdentityId());

                // If this user table hasn't been made yet, we need to create it
                if (happinessThresholdDO == null) {
                    updateDataBase();
                } else {
                    String thresholdFromDB = happinessThresholdDO.getHappinessThreshold();
                    if (thresholdFromDB != null) {
                        threshold = Double.parseDouble(thresholdFromDB);
                    }
                }
            }
        }).start();
    }


    private void updateDataBase() {
        final HappinessThresholdDO happinessThresholdDO = new HappinessThresholdDO();

        new Thread(new Runnable() {
            @Override
            public void run() {
                // We set the userID, as it is the key
                CognitoCachingCredentialsProvider provider =
                        (CognitoCachingCredentialsProvider) AWSMobileClient.getInstance().getCredentialsProvider();
                happinessThresholdDO.setUserId(provider.getIdentityId());
                happinessThresholdDO.setHappinessThreshold(String.valueOf(threshold));
                dynamoDBMapper.save(happinessThresholdDO);
            }
        }).start();
    }
}
