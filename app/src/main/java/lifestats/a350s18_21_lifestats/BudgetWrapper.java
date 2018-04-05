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

public class BudgetWrapper {
    private HashMap<String,Double> thisMapping;
    private HashSet<String> newDBValues;
    private DynamoDBMapper dynamoDBMapper;
    private AmazonDynamoDBClient dynamoDBClient;
    private static BudgetWrapper thisInstance;


    public static BudgetWrapper getInstance() {
        if (thisInstance == null) {
            thisInstance = new BudgetWrapper();
        }
        return thisInstance;
    }

    private BudgetWrapper () {
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

    public int size() {
        return thisMapping.size();
    }


    public void remove(String key) {
        thisMapping.remove(key);
        updateDataBase();
    }

    public void clear() {
        thisMapping.clear();
        updateDataBase();
    }

    public boolean containsKey(String key) {
        return thisMapping.containsKey(key);
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
                BudgetDO budgetDO = dynamoDBMapper.load(
                        BudgetDO.class,
                        provider.getIdentityId());

                // If this user table hasn't been made yet, we need to create it
                if (budgetDO == null) {
                    updateDataBase();
                } else {
                    Set<String> entries = budgetDO.getWeekToBudget();

                    if (entries != null) {
                        for (String entry : entries) {
                            String[] keyValue = entry.split("&");
                            thisMapping.put(keyValue[0], Double.parseDouble(keyValue[1]));
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
        final BudgetDO budgetDO = new BudgetDO();


        for (Map.Entry<String, Double> entry : thisMapping.entrySet()) {
            String key = entry.getKey();
            String value = Double.toString(entry.getValue());
            String toAdd = key + "&" + value;
            newDBValues.add(toAdd);
        }


        new Thread(new Runnable() {
            @Override
            public void run() {
                // We set the userID, as it is the key
                CognitoCachingCredentialsProvider provider =
                        (CognitoCachingCredentialsProvider) AWSMobileClient.getInstance().getCredentialsProvider();
                budgetDO.setUserId(provider.getIdentityId());
                budgetDO.setWeekToBudget(newDBValues);
                dynamoDBMapper.save(budgetDO);
            }
        }).start();


    }

}
