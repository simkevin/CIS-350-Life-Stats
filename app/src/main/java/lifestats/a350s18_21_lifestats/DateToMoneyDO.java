package lifestats.a350s18_21_lifestats;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

import java.util.Set;

@DynamoDBTable(tableName = "lifestats-mobilehub-2078005233-dateToMoney")

public class DateToMoneyDO {
    private String _userId;
    private Set<String> _dateToMoney;

    @DynamoDBHashKey(attributeName = "userId")
    @DynamoDBAttribute(attributeName = "userId")
    public String getUserId() {
        return _userId;
    }

    public void setUserId(final String _userId) {
        this._userId = _userId;
    }
    @DynamoDBAttribute(attributeName = "dateToMoney")
    public Set<String> getDateToMoney() {
        return _dateToMoney;
    }

    public void setDateToMoney(final Set<String> _dateToMoney) {
        this._dateToMoney = _dateToMoney;
    }

}

