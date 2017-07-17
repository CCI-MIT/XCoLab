package org.xcolab.client.admin.attributes.platform;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import org.xcolab.util.attributes.AbstractAttribute;

public class PlatformAttribute extends AbstractAttribute {

    public PlatformAttribute(String property) {
        //platform attributes don't use additionalIds
        setAdditionalId(0L);

        setStringValue(property);
        if (StringUtils.isNotEmpty(property)) {
            if (NumberUtils.isCreatable(property)) {
                Number number = NumberUtils.createNumber(property);
                setNumericValue(number.longValue());
                setRealValue(number.doubleValue());
            }
        }
    }
}
