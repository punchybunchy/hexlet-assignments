package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
public class FileKV implements KeyValueStorage {
    String path;
    Map<String, String> data = new HashMap<>();

    public FileKV(String path, Map<String, String> initialMap) {
        this.path = path;
        data.clear();
        data.putAll(initialMap);
        String content = Utils.serialize(data);
        Utils.writeFile(this.path, content);
    }


    @Override
    public void set(String key, String value) {
        String content = Utils.readFile(path);
        Map <String, String> tempMap = Utils.unserialize(content);
        tempMap.put(key, value);
        KeyValueStorage item = new FileKV(path, tempMap);
    }

    @Override
    public void unset(String key) {
        String content = Utils.readFile(path);
        Map <String, String> tempMap = Utils.unserialize(content);
        tempMap.remove(key);
        KeyValueStorage item = new FileKV(path, tempMap);
    }

    @Override
    public String get(String key, String defaultValue) {
        String content = Utils.readFile(path);
        Map <String, String> tempMap = Utils.unserialize(content);
        if (tempMap.containsKey(key)) {
            return tempMap.get(key);
        }
        return defaultValue;
    }

    @Override
    public Map<String, String> toMap() {
        String content = Utils.readFile(path);
        Map <String, String> resultMap = Utils.unserialize(content);
        return resultMap;
    }
}
// END
