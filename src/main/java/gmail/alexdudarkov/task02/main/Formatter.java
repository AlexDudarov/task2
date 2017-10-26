package gmail.alexdudarkov.task02.main;


import gmail.alexdudarkov.task02.dao.model.Entity;

import java.util.List;
import java.util.Map;

public class Formatter {

    public String format(Entity entity){
      String entityView=  format(entity, 0);
      return entityView;
    }

    private String format(Entity entity, int level) {

        StringBuilder sb = new StringBuilder();
        String name = entity.getName();
        String value = entity.getValue();
        Map<String, String> attributes = entity.getAttributes();
        List<Entity> children = entity.getChildren();
        boolean isProperty = !(value == null) && attributes.isEmpty();
        String tab= "";

        for (int i = 0; i <= level; i++) {

            tab = tab + '\t';
        }
        sb.append(tab).append(name);

        if (isProperty) {
            sb.append(" : ").append(value);
        } else {
            sb.append("{\n");
        }

        if (!attributes.isEmpty()) {
            sb = formatAttributes(attributes, sb);
        }

        if (!children.isEmpty()) {
            level++;
            for (Entity child : children) {
                sb.append(tab).append(format(child, level));
            }
        }

        if (isProperty) {
            sb.append('\n');
        } else {
            sb.append(tab).append(tab).append("}\n");
        }

        return sb.toString();
    }

    private StringBuilder formatAttributes(Map<String, String> attributes, StringBuilder sb) {
        for (Map.Entry<String, String> entry : attributes.entrySet()) {


            String attributeName = entry.getKey();
            String attributeValue = entry.getValue();

            sb.append("\t").append(attributeName).append(" : ")
                    .append(attributeValue).append('\n');
        }

        return sb;

    }
}
