package demonstrations;

import dated_map.DatedMap;
import dated_map.DatedMapImpl;

public class DatedMapTest {
    public static void main(String[] args) {
        DatedMap datedMap = new DatedMapImpl();
        datedMap.put("test", "one");
        System.out.println(datedMap.getKeyLastInsertionDate("test"));
        datedMap.put("test", "one");
        System.out.println(datedMap.getKeyLastInsertionDate("test"));
    }
}
