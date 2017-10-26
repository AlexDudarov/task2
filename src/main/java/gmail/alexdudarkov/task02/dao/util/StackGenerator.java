package gmail.alexdudarkov.task02.dao.util;


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


    private static final String OPEN_TAG = "<([A-Za-z]|_|:).*[^/]>";
    private static final String CLOSE_TAG = "</([A-Za-z]|_|:).*>";
    private static final String VALUE = "[^<].*";
    private static final String SINGLE_TAG = "<([A-Za-z]|_|:).*/>";


    public List<MetaEntity> createStack(List<String> xmlData) {

        List<MetaEntity> metaEntities = new ArrayList<>();

        MetaEntity entity;

        for (String tagData : xmlData) {

            if (tagData.matches(OPEN_TAG)) {

                entity = createOpenTagEntity(tagData);
                metaEntities.add(entity);

            }

            if (tagData.matches(CLOSE_TAG)) {

                entity = createCloseTagEntity();
                metaEntities.add(entity);

            }

            if (tagData.matches(SINGLE_TAG)) {

                entity = createSingleTagEntity(tagData);
                metaEntities.add(entity);

            }

            if (tagData.matches(VALUE)) {


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
