package lifestats.a350s18_21_lifestats;

import android.util.Log;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.auth.CognitoCredentialsProvider;
import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;


/**
 * Created by steph on 2/23/2018.
 */
public class GoalsToDifficultyWrapper {

    private HashMap<String,String> thisMapping;
    private HashSet<String> newDBValues;
    private DynamoDBMapper dynamoDBMapper;
    private AmazonDynamoDBClient dynamoDBClient;
    private static GoalsToDifficultyWrapper thisInstance;

    public static GoalsToDifficultyWrapper getInstance() {
        if (thisInstance == null) {
            thisInstance = new GoalsToDifficultyWrapper();
        }
        return thisInstance;
    }

    private GoalsToDifficultyWrapper () {
        thisMapping = new HashMap<String, String>();
        this.dynamoDBClient = new AmazonDynamoDBClient(AWSMobileClient.getInstance().getCredentialsProvider());
        this.dynamoDBMapper = DynamoDBMapper.builder()
                .dynamoDBClient(dynamoDBClient)
                .awsConfiguration(AWSMobileClient.getInstance().getConfiguration())
                .build();
        getDataBase();
    }


    public String put(String key, String value) {
        thisMapping.put(key, value);
        updateDataBase();
        return value;
    }

    public void remove(String key) {
        thisMapping.remove(key);
        updateDataBase();
    }

    public void clear() {
        thisMapping.clear();
        updateDataBase();
    }

    public Set<Map.Entry<String,String>> entrySet() {
        return thisMapping.entrySet();
    }

    public Set<String> keySet() {return thisMapping.keySet();}

    public String get(String s) {
        return thisMapping.get(s);
    }

    private void getDataBase() {

        new Thread(new Runnable() {
            @Override
            public void run() {

                // We set the userID, as it is the key
                CognitoCachingCredentialsProvider provider =
                        (CognitoCachingCredentialsProvider) AWSMobileClient.getInstance().getCredentialsProvider();
                GoalsToDifficultyDO goalsToDifficultyDO = dynamoDBMapper.load(
                        GoalsToDifficultyDO.class,
                        provider.getIdentityId());
                if (goalsToDifficultyDO == null){
                    updateDataBase();
                } else {
                    Set<String> entries = goalsToDifficultyDO.getGoalsToDifficulty();
                    if (entries != null) {
                        for (String entry : entries) {
                            String[] keyValue = entry.split(":");
                            thisMapping.put(keyValue[0], keyValue[1]);
                        }
                    }
                }
            }
        }).start();
    }


    private void updateDataBase() {

        // If there are no entries to put into the database, then we return.
        if (thisMapping.isEmpty()){
            return;
        }
        newDBValues = new HashSet<String>();
        final GoalsToDifficultyDO goalsToDifficultyDO = new GoalsToDifficultyDO();


        for (Map.Entry<String, String> entry : thisMapping.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            String toAdd = key + ":" + value;
            newDBValues.add(toAdd);
        }


        new Thread(new Runnable() {
            @Override
            public void run() {
                // We set the userID, as it is the key
                CognitoCachingCredentialsProvider provider =
                        (CognitoCachingCredentialsProvider) AWSMobileClient.getInstance().getCredentialsProvider();
                goalsToDifficultyDO.setUserId(provider.getIdentityId());
                goalsToDifficultyDO.setGoalsToDifficulty(newDBValues);
                dynamoDBMapper.save(goalsToDifficultyDO);
            }
        }).start();


    }

}
