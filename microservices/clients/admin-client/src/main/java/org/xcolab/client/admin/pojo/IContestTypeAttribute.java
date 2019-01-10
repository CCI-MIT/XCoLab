package org.xcolab.client.admin.pojo;

import org.xcolab.commons.attributes.Attribute;

public interface IContestTypeAttribute extends Attribute {

    String getName();

    void setName(String name);

    Long getAdditionalId();

    void setAdditionalId(Long additionalId);

    String getLocale();

    void setLocale(String locale);

    Long getNumericValue();

    void setNumericValue(Long numericValue);

    String getStringValue();

    void setStringValue(String stringValue);

    Double getRealValue();

    void setRealValue(Double realValue);
}
