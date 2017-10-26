package gmail.alexdudarkov.task02.dao.util;


import gmail.alexdudarkov.task02.dao.model.Entity;
import gmail.alexdudarkov.task02.dao.model.MetaEntity;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class Builder {

  private Deque<Entity> entities = new ArrayDeque<>();


    public Entity build(List<MetaEntity> metaEntities) {

        openTag(metaEntities.get(0));

        for (int i = 1; i < metaEntities.size() - 1; i++) {
            MetaEntity metaEntity = metaEntities.get(i);
            switch (metaEntity.getTagType()) {

                case VALUE: {
                    valueTag(metaEntity);
                    break;
                }

                case OPEN_TAG: {
                    openTag(metaEntity);
                    break;
                }

                case SINGLE_TAG: {
                    singleTag(metaEntity);
                    break;
                }

                case CLOSE_TAG: {
                    closeTag();
                    break;
                }

                default: {
                    System.out.println("Ошибка");
                    break;
                }

            }

        }

        return entities.getLast();
    }

    private void singleTag(MetaEntity metaEntity) {

        openTag(metaEntity);
        closeTag();
    }

    private void valueTag(MetaEntity metaEntity) {

        Entity entity = entities.getLast();
        entity.setValue(metaEntity.getValue());
    }

    private void openTag(MetaEntity metaEntity) {

        Entity e = new Entity();
        e.setName(metaEntity.getValue());
        if (!metaEntity.getMap().isEmpty()) {
            e.setAttributes(metaEntity.getMap());
        }
        entities.addLast(e);

    }

    private void closeTag() {

        Entity child = entities.pollLast();
        Entity parent = entities.getLast();
        parent.addChildren(child);
        child.setParent(parent);

    }
}
