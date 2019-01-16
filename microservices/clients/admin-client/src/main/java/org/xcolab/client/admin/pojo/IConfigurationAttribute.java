package org.xcolab.client.admin.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.xcolab.client.admin.pojo.tables.pojos.ConfigurationAttribute;
import org.xcolab.commons.attributes.Attribute;

@JsonDeserialize(as = ConfigurationAttribute.class)
public interface IConfigurationAttribute extends Attribute {

    //TODO: default value was:
    //    public ConfigurationAttribute() {
    //        setAdditionalId(0L);
    //        setLocale("");
    //    }

    String getName();

    void setName(String name);

    Long getAdditionalId();

    void setAdditionalId(Long additionalId);

    Long getNumericValue();

    void setNumericValue(Long numericValue);

    String getStringValue();

    void setStringValue(String stringValue);

    Double getRealValue();

    void setRealValue(Double realValue);

    String getLocale();

    void setLocale(String locale);
}
