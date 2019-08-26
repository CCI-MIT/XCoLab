package org.xcolab.commons.monitoring;

import io.micrometer.core.instrument.Tag;

public class Parameter implements Tag {

    private String _key;
    private String _value;

    public Parameter(String key, String value){
        _key = key;
        _value = value;
    }

    @Override
    public String getKey() {
        return _key;
    }

    @Override
    public String getValue() {
        return _value;
    }
}
