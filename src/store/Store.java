package store;

import java.util.*;

public class Store {
    Map<String, MyObject> stringObjectMap = new HashMap<>();

    public void put(String key, List<Pair<String, String>> pairList) {
        Iterator<Pair<String, String>> iterator = pairList.iterator();

        MyObject object = stringObjectMap.get(key) == null ? new MyObject() : stringObjectMap.get(key);

        while (iterator.hasNext()) {
            Pair<String, String> pair = iterator.next();
            String valType = "string";

            if (pair.getValue().matches("[0-9]+")) {
                int val = Integer.parseInt(pair.getValue());
                valType = "int";
            } else if (pair.getValue().matches("[0-9]+[.][0-9]+")) {
                double val = Float.parseFloat(pair.getValue());
                valType = "double";
            } else if (pair.getValue().matches("^(?i)(true|false)$")) {
                boolean val = Boolean.parseBoolean(pair.getValue());
                valType = "boolean";
            }

            if (valType == "int") {
                Wrapper<Integer> wrapClass = new Wrapper<>(Integer.class);
                wrapClass.setVal(Integer.parseInt(pair.getValue()));
                object.setProperty(pair.getKey(), wrapClass);
            } else if (valType == "string") {
                Wrapper<String> wrapClass = new Wrapper<>(String.class);
                wrapClass.setVal(pair.getValue());
                object.setProperty(pair.getKey(), wrapClass);
            } else if (valType == "double") {
                Wrapper<Double> wrapClass = new Wrapper<>(Double.class);
                wrapClass.setVal(Double.parseDouble(pair.getValue()));
                object.setProperty(pair.getKey(), wrapClass);
            } else {
                Wrapper<Boolean> wrapClass = new Wrapper<>(Boolean.class);
                wrapClass.setVal(Boolean.parseBoolean(pair.getValue()));
                object.setProperty(pair.getKey(), wrapClass);
            }

        }
        stringObjectMap.put(key, object);
    }

    public String get(String key) {
        MyObject res = stringObjectMap.get(key);

        if (res == null) {
            return null;
        }

        return res.toString();
    }

    public void delete(String key) {
        stringObjectMap.remove(key);
    }

    public String keys() {
        Set<String> list = stringObjectMap.keySet();

        if (list.size() == 0) {
            return "";
        }
        Iterator<String> iterator = list.iterator();

        String res = "";
        while (iterator.hasNext()) {
            res += iterator.next() + ",";
        }

        return res.substring(0, res.length() - 1);
    }
}
