package lifestats.a350s18_21_lifestats;
import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import android.util.Log;


/**
 * Created by steph on 3/23/2018.
 */

public class SleepStatusWrapper {
    private LinkedList<SleepElement> sleepTrack;
    private DynamoDBMapper dynamoDBMapper;
    private AmazonDynamoDBClient dynamoDBClient;
    private static SleepStatusWrapper thisInstance;


    public static SleepStatusWrapper getInstance() {
        if (thisInstance == null) {
            thisInstance = new SleepStatusWrapper();
        }
        return thisInstance;
    }

    private SleepStatusWrapper () {
        this.dynamoDBClient = new AmazonDynamoDBClient(AWSMobileClient.getInstance().getCredentialsProvider());
        this.dynamoDBMapper = DynamoDBMapper.builder()
                .dynamoDBClient(dynamoDBClient)
                .awsConfiguration(AWSMobileClient.getInstance().getConfiguration())
                .build();
        sleepTrack = new LinkedList<SleepElement>();
        getDataBase();
    }


    public int size() {
        return sleepTrack.size();
    }

    public boolean isEmpty() {
        return sleepTrack.isEmpty();
    }

    public SleepElement getLast() {
        return sleepTrack.getLast();
    }

    public void add(SleepElement toAdd) {
        sleepTrack.add(toAdd);
        updateDataBase();
    }

    public Iterator<SleepElement> iterator() {
        return sleepTrack.iterator();
    }

    public SleepElement peekFirst() {
        return sleepTrack.peekFirst();
    }

    private void getDataBase() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                // We set the userID, as it is the key
                CognitoCachingCredentialsProvider provider =
                        (CognitoCachingCredentialsProvider) AWSMobileClient.getInstance().getCredentialsProvider();
                SleepStatusDO sleepStatusDO = dynamoDBMapper.load(
                        SleepStatusDO.class,
                        provider.getIdentityId());

                // If this user table hasn't been made yet, we need to create it
                if (sleepStatusDO == null || sleepStatusDO.equals("")) {
                    updateDataBase();
                } else {
                    String sleepStatus = sleepStatusDO.getSleepStatus();
                    if (sleepStatus == null) {
                        return;
                    }
                    String[] entries = sleepStatus.split("&");
                    for (int i = 0; i < entries.length; i++) {
                        String[] thisEntry = entries[i].split("!");
                        String date = thisEntry[0];
                        boolean status = Boolean.parseBoolean(thisEntry[1]);
                        SleepElement toAdd = new SleepElement(date, status);
                        sleepTrack.add(toAdd);
                    }
                }
            }
        }).start();
    }


    private void updateDataBase() {
        final SleepStatusDO sleepStatusDO = new SleepStatusDO();

        new Thread(new Runnable() {
            @Override
            public void run() {
                // We set the userID, as it is the key
                CognitoCachingCredentialsProvider provider =
                        (CognitoCachingCredentialsProvider) AWSMobileClient.getInstance().getCredentialsProvider();
                sleepStatusDO.setUserId(provider.getIdentityId());

                StringBuilder toUpload = new StringBuilder();

                for (SleepElement element : sleepTrack) {
                    toUpload.append(element.date);
                    toUpload.append("!");
                    toUpload.append(element.sleepStatus);
                    toUpload.append("&");

                }
                String toUploadString = toUpload.toString();
                if(!toUploadString.equals("")) {
                    toUploadString = toUploadString.substring(0, toUploadString.length() - 1);
                }

                sleepStatusDO.setSleepStatus(toUploadString);
                dynamoDBMapper.save(sleepStatusDO);
            }
        }).start();
    }
}
