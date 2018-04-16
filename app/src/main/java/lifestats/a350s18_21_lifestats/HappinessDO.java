package lifestats.a350s18_21_lifestats;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

import java.util.Set;

@DynamoDBTable(tableName = "lifestats-mobilehub-2078005233-happiness")

public class HappinessDO {
    private String _userId;
    private Set<String> _hapiness;

    @DynamoDBHashKey(attributeName = "userId")
    @DynamoDBAttribute(attributeName = "userId")
    public String getUserId() {
        return _userId;
    }

    public void setUserId(final String _userId) {
        this._userId = _userId;
    }
    @DynamoDBAttribute(attributeName = "hapiness")
    public Set<String> getHapiness() {
        return _hapiness;
    }

    public void setHapiness(final Set<String> _hapiness) {
        this._hapiness = _hapiness;
    }

}
