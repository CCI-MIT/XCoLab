package org.xcolab.commons.html;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public class LabelStringValue {

    private String lable;
    private String value;

    public LabelStringValue(String value, String lable) {
        this.value = value;
        this.lable = lable;
    }

    public static Collection<LabelStringValue> fromMap(Map<String, String> labelToValueMap) {
        return labelToValueMap.entrySet().stream()
                .map(entry -> new LabelStringValue(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }
}
