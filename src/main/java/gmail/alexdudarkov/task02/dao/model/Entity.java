package gmail.alexdudarkov.task02.dao.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Entity implements TreeEntity, Serializable {

    private static final long serialVersionUID = 1L;

    private Entity parent;
    private String name;
    private String value;
    private Map<String, String> attributes = new HashMap<>();
    private List<Entity> children = new ArrayList<>();

    public Entity getParent() {
        return parent;
    }

    public void setParent(Entity parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    public List<Entity> getChildren() {
        return children;
    }

    public void setChildren(List<Entity> children) {
        this.children = children;
    }

    public void addChildren(Entity entity) {
        children.add(entity);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Entity)) return false;

        Entity entity = (Entity) o;

        if (name != null ? !name.equals(entity.name) : entity.name != null) return false;
        if (value != null ? !value.equals(entity.value) : entity.value != null) return false;
        if (attributes != null ? !attributes.equals(entity.attributes) : entity.attributes != null) return false;
        return children != null ? children.equals(entity.children) : entity.children == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (attributes != null ? attributes.hashCode() : 0);
        result = 31 * result + (children != null ? children.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", attributes=" + attributes +
                ", children=" + children +
                '}';
    }
}
