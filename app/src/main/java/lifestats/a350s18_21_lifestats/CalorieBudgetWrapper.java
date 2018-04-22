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

public class CalorieBudgetWrapper {
    private int budget = 2000; // The default calorie budget
    private DynamoDBMapper dynamoDBMapper;
    private AmazonDynamoDBClient dynamoDBClient;
    private static CalorieBudgetWrapper thisInstance;


    public static CalorieBudgetWrapper getInstance() {
        if (thisInstance == null) {
            thisInstance = new CalorieBudgetWrapper();
        }
        return thisInstance;
    }

    private CalorieBudgetWrapper () {
        this.dynamoDBClient = new AmazonDynamoDBClient(AWSMobileClient.getInstance().getCredentialsProvider());
        this.dynamoDBMapper = DynamoDBMapper.builder()
                .dynamoDBClient(dynamoDBClient)
                .awsConfiguration(AWSMobileClient.getInstance().getConfiguration())
                .build();
        getDataBase();
    }


    public void setBudget(int budget) {
        this.budget = budget;
        updateDataBase();
    }

    public int getBudget() {
        return budget;
    }

    private void getDataBase() {

        new Thread(new Runnable() {
            @Override
            public void run() {

                // We set the userID, as it is the key
                CognitoCachingCredentialsProvider provider =
                        (CognitoCachingCredentialsProvider) AWSMobileClient.getInstance().getCredentialsProvider();
                CalorieBudgetDO calorieBudgetDO = dynamoDBMapper.load(
                        CalorieBudgetDO.class,
                        provider.getIdentityId());

                // If this user table hasn't been made yet, we need to create it
                if (calorieBudgetDO == null) {
                    updateDataBase();
                } else {
                    String budgetFromDB = calorieBudgetDO.getCalorieBudget();
                    if (budgetFromDB != null) {
                        budget = Integer.parseInt(budgetFromDB);
                    }
                }
            }
        }).start();
    }


    private void updateDataBase() {
        final CalorieBudgetDO calorieBudgetDO = new CalorieBudgetDO();

        new Thread(new Runnable() {
            @Override
            public void run() {
                // We set the userID, as it is the key
                CognitoCachingCredentialsProvider provider =
                        (CognitoCachingCredentialsProvider) AWSMobileClient.getInstance().getCredentialsProvider();
                calorieBudgetDO.setUserId(provider.getIdentityId());
                calorieBudgetDO.setCalorieBudget(String.valueOf(budget));
                dynamoDBMapper.save(calorieBudgetDO);
            }
        }).start();
    }
}
