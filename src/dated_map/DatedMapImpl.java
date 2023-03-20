package dated_map;

import java.util.Date;
import java.util.HashMap;
import java.util.Set;

public class DatedMapImpl implements DatedMap {

    private final HashMap<String, String> map = new HashMap<>();
    private final HashMap<String, Date> dateMap = new HashMap<>();

    @Override
    public void put(String key, String value) {
        dateMap.putIfAbsent(key, new Date());
        map.put(key, value);
    }


    @Override
    public String get(String key) {
        return map.get(key);
    }

    @Override
    public boolean containsKey(String key) {
        return map.containsKey(key);
    }

    @Override
    public void remove(String key) {
        dateMap.remove(key);
        map.remove(key);
    }

    @Override
    public Set<String> keySet() {
        return map.keySet();
    }

    @Override
    public Date getKeyLastInsertionDate(String key) {
        return dateMap.get(key);
    }
}
