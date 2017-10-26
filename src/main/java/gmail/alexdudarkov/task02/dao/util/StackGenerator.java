package gmail.alexdudarkov.task02.dao.util;


import gmail.alexdudarkov.task02.dao.model.MetaEntity;
import gmail.alexdudarkov.task02.dao.model.TagType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StackGenerator {

    private static final Pattern TAG_NAME_PATTERN=Pattern.compile("<[/\\s]?([^\\s>/]*)[\\s>/]");
    private static final Pattern ATTRIBUTE_NAME_PATTERN=Pattern.compile("(\\S*)\\s*=");
    private static final Pattern ATTRIBUTE_VALUE_PATTERN=Pattern.compile("=\\s*['|\"]([^'\"]*)['|\"]");


    private static final String OPEN_TAG = "<([A-Za-z]|_|:).*[^/]>";
    private static final String CLOSE_TAG = "</([A-Za-z]|_|:).*>";
    private static final String VALUE = "[^<].*";
    private static final String SINGLE_TAG = "<([A-Za-z]|_|:).*/>";

    private List<MetaEntity> metaEntities = new ArrayList<>();


    public List<MetaEntity> createStack(List<String> list) {

        MetaEntity entity;
        for (String str : list) {

            if (str.matches(OPEN_TAG)) {

                entity = new MetaEntity();
                entity.setMap(parseAttributes(str));
                entity.setValue(parseName(str));
                entity.setTagType(TagType.OPEN_TAG);
                metaEntities.add(entity);

            }

            if (str.matches(CLOSE_TAG)) {

                entity = new MetaEntity();
                entity.setTagType(TagType.CLOSE_TAG);
                metaEntities.add(entity);

            }

            if (str.matches(SINGLE_TAG)) {

                entity = new MetaEntity();
                entity.setMap(parseAttributes(str));
                entity.setValue(parseName(str));
                entity.setTagType(TagType.SINGLE_TAG);
                metaEntities.add(entity);

            }

            if (str.matches(VALUE)) {


                entity = new MetaEntity();
                entity.setValue(str);
                entity.setTagType(TagType.VALUE);
                metaEntities.add(entity);

            }
        }
        return metaEntities;
    }

    public static Map<String, String> parseAttributes(String data) {
        Map<String, String> mapData = new HashMap<>(5);

        Matcher nameMatcher = ATTRIBUTE_NAME_PATTERN.matcher(data);
        Matcher valueMatcher = ATTRIBUTE_VALUE_PATTERN.matcher(data);

        while (nameMatcher.find() && valueMatcher.find()) {
            mapData.put(nameMatcher.group(1), valueMatcher.group(1));
        }

        return mapData;
    }

    public String parseName(String data) {

        Matcher tagNameMatcher = TAG_NAME_PATTERN.matcher(data);
        String s=null;
        while (tagNameMatcher.find()) {
            s = tagNameMatcher.group(1);
        }
        return s;
    }


}
