package exercise;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

// BEGIN
public class App {

    public static KeyValueStorage swapKeyValue(KeyValueStorage storage) {
        Map<String, String> map = storage.toMap();

        for (String key : map.keySet()) {
            storage.unset(key);
            storage.set(map.get(key), key);
        }
        return storage;
    }
}
// END
