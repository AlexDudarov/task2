package gmail.alexdudarkov.task02.dao.util;


import gmail.alexdudarkov.task02.dao.exception.DAOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StackGenerator {

    private static final Pattern TAG_NAME_PATTERN = Pattern.compile("<[/\\s]?([^\\s>/]*)[\\s>/]");
    private static final Pattern ATTRIBUTE_NAME_PATTERN = Pattern.compile("(\\S*)\\s*=");
    private static final Pattern ATTRIBUTE_VALUE_PATTERN = Pattern.compile("=\\s*['|\"]([^'\"]*)['|\"]");

    private static final Pattern OPEN_TAG = Pattern.compile("<([A-Za-z]|_|:).*[^/]>");
    private static final Pattern CLOSE_TAG = Pattern.compile("</([A-Za-z]|_|:).*>");
    private static final Pattern VALUE = Pattern.compile("[^<].*");
    private static final Pattern SINGLE_TAG = Pattern.compile("<([A-Za-z]|_|:).*/>");


    public List<MetaEntity> createStack(List<String> xmlData) throws DAOException {

        List<MetaEntity> metaEntities = new ArrayList<>();

        MetaEntity entity;

        for (String tagData : xmlData) {

            if (OPEN_TAG.matcher(tagData).matches()) {

                entity = createOpenTagEntity(tagData);
                metaEntities.add(entity);
                continue;
            }

            if (CLOSE_TAG.matcher(tagData).matches()) {

                entity = createCloseTagEntity();
                metaEntities.add(entity);
                continue;
            }

            if (SINGLE_TAG.matcher(tagData).matches()) {

                entity = createSingleTagEntity(tagData);
                metaEntities.add(entity);
                continue;
            }

            if (VALUE.matcher(tagData).matches()) {


                entity = createValueEntity(tagData);
                metaEntities.add(entity);

            }
        }
        return metaEntities;
    }


    private MetaEntity createValueEntity(String data) {

        MetaEntity metaEntity = new MetaEntity();
        metaEntity.setValue(data);
        metaEntity.setTagType(TagType.VALUE);

        return metaEntity;
    }

    private MetaEntity createCloseTagEntity() {

        MetaEntity metaEntity = new MetaEntity();
        metaEntity.setTagType(TagType.CLOSE_TAG);

        return metaEntity;
    }

    private MetaEntity createOpenTagEntity(String data) {

        MetaEntity metaEntity = new MetaEntity();
        metaEntity.setMap(parseAttributes(data));
        metaEntity.setValue(parseName(data));
        metaEntity.setTagType(TagType.OPEN_TAG);

        return metaEntity;
    }

    private MetaEntity createSingleTagEntity(String data) {

        MetaEntity metaEntity = new MetaEntity();
        metaEntity.setMap(parseAttributes(data));
        metaEntity.setValue(parseName(data));
        metaEntity.setTagType(TagType.SINGLE_TAG);

        return metaEntity;
    }

    private static Map<String, String> parseAttributes(String data) {

        Map<String, String> mapData = new HashMap<>(5);

        Matcher nameMatcher = ATTRIBUTE_NAME_PATTERN.matcher(data);
        Matcher valueMatcher = ATTRIBUTE_VALUE_PATTERN.matcher(data);

        while (nameMatcher.find() && valueMatcher.find()) {
            mapData.put(nameMatcher.group(1), valueMatcher.group(1));
        }

        return mapData;
    }

    private String parseName(String data) {

        Matcher tagNameMatcher = TAG_NAME_PATTERN.matcher(data);
        String s = null;
        while (tagNameMatcher.find()) {
            s = tagNameMatcher.group(1);
        }
        return s;
    }


}
