package lifestats.a350s18_21_lifestats;;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

import java.util.List;
import java.util.Map;
import java.util.Set;

@DynamoDBTable(tableName = "lifestats-mobilehub-2078005233-goalsToDifficulty")

public class GoalsToDifficultyDO {
    private String _userId;
    private Set<String> _goalsToDifficulty;

    @DynamoDBHashKey(attributeName = "userId")
    @DynamoDBAttribute(attributeName = "userId")
    public String getUserId() {
        return _userId;
    }

    public void setUserId(final String _userId) {
        this._userId = _userId;
    }
    @DynamoDBAttribute(attributeName = "goalsToDifficulty")
    public Set<String> getGoalsToDifficulty() {
        return _goalsToDifficulty;
    }

    public void setGoalsToDifficulty(final Set<String> _goalsToDifficulty) {
        this._goalsToDifficulty = _goalsToDifficulty;
    }

}

