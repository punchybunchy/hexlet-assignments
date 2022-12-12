package exercise;

import java.util.stream.Collectors;
import java.util.Map;

// BEGIN
public class Tag {
    private final String tagTitle;
    private final Map<String, String> tagAttribute;

    public String getTagTitle() {
        return tagTitle;
    }

    public Map<String, String> getTagAttribute() {
        return tagAttribute;
    }

    public Tag(String tagTitle, Map<String, String> tagAttribute) {
        this.tagTitle = tagTitle;
        this.tagAttribute = tagAttribute;
    }

    public String toString() {
        if (getTagAttribute().isEmpty()) {
            return String.format("<%s>", getTagTitle());
        }
        String tagAttribute = getTagAttribute().entrySet().stream()
                .map(item -> String.format("%s=\"%s\"", item.getKey(), item.getValue()))
                .collect(Collectors.joining(" "));
        return String.format("<%s %s>", getTagTitle(), tagAttribute);
    }
}
// END
