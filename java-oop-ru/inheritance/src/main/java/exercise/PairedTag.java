package exercise;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class PairedTag extends Tag {

    private final String bodyText;
    private final List<Tag> singleTags;
    public PairedTag(String tagTitle, Map<String, String> tagAttribute, String bodyText, List<Tag> singleTags) {
        super(tagTitle, tagAttribute);
        this.bodyText = bodyText;
        this.singleTags = singleTags;
    }

    @Override
    public String toString() {
        String titleAndAtt = new Tag(getTagTitle(), getTagAttribute()).toString();
        String tags = singleTags.stream()
                .map(Tag::toString)
                .collect(Collectors.joining(""));
        return String.format("%s%s%s</%s>", titleAndAtt, bodyText, tags, super.getTagTitle());
    }
}
// END
