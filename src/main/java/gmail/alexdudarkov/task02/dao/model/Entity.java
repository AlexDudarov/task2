package gmail.alexdudarkov.task02.dao.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Entity implements Serializable {

    private Entity parent;
    private String name;
    private String value;
    private Map<String, String> attributes=new HashMap<>();
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

    public void addChildren(Entity entity){children.add(entity);}


    @Override
    public String toString() {

        return "Entity{" +"\n"+
                "name='" + name + '\'' +"\n"+
                ", value='" + value + '\''+"\n"+
                ", attributes=" + attributes +"\n"+
                ", children=" + children +"\n"+
                '}';
    }


}
