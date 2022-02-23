package store;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MyObject {
    Map<String, Wrapper> stringObjectMap = new HashMap<>();

    public void setProperty(String key, Wrapper value) {
        if (stringObjectMap.get(key) != null && stringObjectMap.get(key).getType() != value.getType()) {
            throw new RuntimeException("Can't change data type on the go");
        }
        stringObjectMap.put(key, value);
    }

    public Wrapper getProperty(String key) {
        return stringObjectMap.get(key);
    }

    public String toString() {
        Iterator<Map.Entry<String, Wrapper>> iterator = stringObjectMap.entrySet().iterator();

        if (stringObjectMap.size() == 0) {
            return null;
        }

        String res = "";

        while (iterator.hasNext()) {
            Map.Entry<String, Wrapper> pair = iterator.next();
            res += pair.getKey() + ": " + pair.getValue().getVal().toString() + ",";
        }
        return res.substring(0, res.length() - 1);
    }
}
