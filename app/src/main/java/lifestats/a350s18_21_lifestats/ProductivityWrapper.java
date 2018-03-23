package lifestats.a350s18_21_lifestats;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by steph on 3/23/2018.
 */

public class ProductivityWrapper {

    private HashMap<String,Float> thisMapping;;

    public ProductivityWrapper (){
        thisMapping = new HashMap<String, Float>();
    }

    public Float put(String key, Float value) {
        thisMapping.put(key, value);
        return value;
    }

    public void clear() {
        thisMapping.clear();
    }

    public Set<Map.Entry<String, Float>> entrySet() {
        return thisMapping.entrySet();
    }

    public Set<String> keySet() {return thisMapping.keySet();}

    public Float get(String s) {
        return thisMapping.get(s);
    }
}
