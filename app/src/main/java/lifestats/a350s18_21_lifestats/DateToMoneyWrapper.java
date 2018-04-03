package lifestats.a350s18_21_lifestats;

import android.util.Log;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by stephen on 4/1/18.
 */

public class DateToMoneyWrapper {
    private HashMap<String,Double> thisMapping;
    private HashSet<String> newDBValues;
    private DynamoDBMapper dynamoDBMapper;
    private AmazonDynamoDBClient dynamoDBClient;
    private static DateToMoneyWrapper thisInstance;

    public static DateToMoneyWrapper getInstance() {
        if (thisInstance == null) {
            thisInstance = new DateToMoneyWrapper();
        }
        return thisInstance;
    }

    private DateToMoneyWrapper () {
        thisMapping = new HashMap<String, Double>();
        this.dynamoDBClient = new AmazonDynamoDBClient(AWSMobileClient.getInstance().getCredentialsProvider());
        this.dynamoDBMapper = DynamoDBMapper.builder()
                .dynamoDBClient(dynamoDBClient)
                .awsConfiguration(AWSMobileClient.getInstance().getConfiguration())
                .build();
        getDataBase();
    }


    public void put(String key, Double value) {
        thisMapping.put(key, value);
        updateDataBase();
    }

    public void remove(String key) {
        thisMapping.remove(key);
        updateDataBase();
    }

    public void clear() {
        thisMapping.clear();
        updateDataBase();
    }

    public Set<Map.Entry<String,Double>> entrySet() {
        return thisMapping.entrySet();
    }

    public Set<String> keySet() {return thisMapping.keySet();}

    public Double get(String s) {
        return thisMapping.get(s);
    }

    private void getDataBase() {

        new Thread(new Runnable() {
            @Override
            public void run() {

                // We set the userID, as it is the key
                CognitoCachingCredentialsProvider provider =
                        (CognitoCachingCredentialsProvider) AWSMobileClient.getInstance().getCredentialsProvider();
                DateToMoneyDO goalsToDifficultyDO = dynamoDBMapper.load(
                        DateToMoneyDO.class,
                        provider.getIdentityId());
                Set<String> entries = goalsToDifficultyDO.getDateToMoney();

                if (entries != null) {
                    for (String entry : entries) {
                        String[] keyValue = entry.split(":");
                        thisMapping.put(keyValue[0], Double.parseDouble(keyValue[1]));
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
        final DateToMoneyDO dateToMoneyDO = new DateToMoneyDO();


        for (Map.Entry<String, Double> entry : thisMapping.entrySet()) {
            String key = entry.getKey();
            String value = Double.toString(entry.getValue());
            String toAdd = key + ":" + value;
            newDBValues.add(toAdd);
        }


        new Thread(new Runnable() {
            @Override
            public void run() {
                // We set the userID, as it is the key
                CognitoCachingCredentialsProvider provider =
                        (CognitoCachingCredentialsProvider) AWSMobileClient.getInstance().getCredentialsProvider();
                dateToMoneyDO.setUserId(provider.getIdentityId());
                dateToMoneyDO.setDateToMoney(newDBValues);
                dynamoDBMapper.save(dateToMoneyDO);
            }
        }).start();


    }


}
