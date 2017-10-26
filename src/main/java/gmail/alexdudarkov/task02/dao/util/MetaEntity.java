package gmail.alexdudarkov.task02.dao.util;


import java.util.HashMap;
import java.util.Map;

public class MetaEntity {

    private TagType tagType;

    private String value;
    private Map<String, String> map = new HashMap<>(5);

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }


    public TagType getTagType() {
        return tagType;
    }

    public void setTagType(TagType tagType) {
        this.tagType = tagType;
    }

    @Override
    public String toString() {
        return "MetaEntity{" +
                "tagType=" + tagType +
                ", value='" + value + '\'' +
                ", map=" + map +
                '}';
    }
}
