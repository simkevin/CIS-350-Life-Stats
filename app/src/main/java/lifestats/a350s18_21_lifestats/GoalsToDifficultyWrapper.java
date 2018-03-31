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
    private GoalsToDifficultyDO goalsToDifficultyDO;
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

    private GoalsToDifficultyWrapper (){
        thisMapping = new HashMap<String, String>();
        this.dynamoDBClient = new AmazonDynamoDBClient(AWSMobileClient.getInstance().getCredentialsProvider());
        this.dynamoDBMapper = DynamoDBMapper.builder()
                .dynamoDBClient(dynamoDBClient)
                .awsConfiguration(AWSMobileClient.getInstance().getConfiguration())
                .build();
        thisMapping.put("Eat Better", "3.0:1499817600000:1521807434000");
        thisMapping.put("Make more money", "4.0:1499817600000:1521807434000");
        thisMapping.put("Get more sleep", "1.0:1499817600000:1521807434000");
    }

    private void initializeDatabase() {

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



    private void updateDataBase() {
        newDBValues = new HashSet<String>();
        final GoalsToDifficultyDO goalsToDifficultyDO = new GoalsToDifficultyDO();

        for (Map.Entry<String, String> entry : thisMapping.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            String toAdd = key + ":" + value;
            newDBValues.add(toAdd);
        }
        Log.d("In GoalsDO", "In GoalsDO and I am TACOOOOOOO");

        new Thread(new Runnable() {
            @Override
            public void run() {
                // We set the userID, as it is the key
                CognitoCachingCredentialsProvider provider =
                        (CognitoCachingCredentialsProvider) AWSMobileClient.getInstance().getCredentialsProvider();
                Log.d("Here is credential", provider.getIdentityId());
                goalsToDifficultyDO.setUserId(provider.getIdentityId());
                goalsToDifficultyDO.setGoalsToDifficulty(newDBValues);
                dynamoDBMapper.save(goalsToDifficultyDO);
            }
        }).start();


    }

}
