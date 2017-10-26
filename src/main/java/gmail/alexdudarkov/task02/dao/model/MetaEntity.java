package gmail.alexdudarkov.task02.dao.model;


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MetaEntity)) return false;

        MetaEntity that = (MetaEntity) o;

        if (value != null ? !value.equals(that.value) : that.value != null) return false;
        return map != null ? map.equals(that.map) : that.map == null;
    }

    @Override
    public int hashCode() {
        int result = value != null ? value.hashCode() : 0;
        result = 31 * result + (map != null ? map.hashCode() : 0);
        return result;
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
