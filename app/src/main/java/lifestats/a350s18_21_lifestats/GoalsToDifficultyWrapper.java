package lifestats.a350s18_21_lifestats;

import com.amazonaws.auth.CognitoCredentialsProvider;
import com.amazonaws.mobile.client.AWSMobileClient;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import lifestats.a350s18_21_lifestats.GoalsToDifficultyDO;

/**
 * Created by steph on 2/23/2018.
 */
public class GoalsToDifficultyWrapper {

    private HashMap<String,String> thisMapping;
    private GoalsToDifficultyDO goalsToDifficultyDO;

    public void GoalsToDifficultWrapper() {
        thisMapping = new HashMap<String, String>();
        goalsToDifficultyDO = new GoalsToDifficultyDO();
        goalsToDifficultyDO.setUserId(((CognitoCredentialsProvider) AWSMobileClient.getInstance()
                .getCredentialsProvider()).getCredentials().getAWSAccessKeyId());
    }

    public String put(String key, String value) {
        thisMapping.put(key, value);
        updateDataBase();
        return value;
    }

    public void clear() {
        thisMapping.clear();
        updateDataBase();
    }

    public Set<Map.Entry<String,String>> entrySet() {
        return thisMapping.entrySet();
    }

    private void updateDataBase() {
        HashSet<String> newDBValues = new HashSet<String>();

        for (Map.Entry<String, String> entry : thisMapping.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            String toAdd = key + ":" + value;
            newDBValues.add(toAdd);
        }
        goalsToDifficultyDO.setGoalsToDifficulty(newDBValues);
    }

}
