package lifestats.a350s18_21_lifestats;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by steph on 3/23/2018.
 */

public class StressWrapper {
    private HashMap<String,Float> thisMapping;
    private static StressWrapper thisInstance;

    private StressWrapper (){
        thisMapping = new HashMap<String, Float>();
        thisMapping.put("2017-02-13 00:00:00", (float) 2.5);
        thisMapping.put("2017-07-13 00:00:00", (float) 1.0);
        thisMapping.put("2018-01-24 00:00:00", (float) 4.0);
    }

    public static StressWrapper getInstance() {
        if (thisInstance == null) {
            thisInstance = new StressWrapper();
        }
        return thisInstance;
    }

    public Float put(String key, Float value) {
        thisMapping.put(key, value);
        return value;
    }

    public void clear() {
        thisMapping.clear();
    }

    public int size() {
        return thisMapping.size();
    }

    public Set<Map.Entry<String, Float>> entrySet() {
        return thisMapping.entrySet();
    }

    public Set<String> keySet() {return thisMapping.keySet();}

    public Float get(String s) {
        return thisMapping.get(s);
    }
}
