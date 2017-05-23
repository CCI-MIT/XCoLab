package org.xcolab.client.proposals.helpers;

import org.xcolab.util.attributes.Attribute;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AttributeHelper<AttributeT extends Attribute> {

    private Map<String, Map<Long, AttributeT>> attributesByNameAndAdditionalId;

    protected abstract List<AttributeT> getAttributes();

    protected abstract boolean isNewRankedHigher(AttributeT oldAttribute, AttributeT newAttribute);

    //initialization is expensive --> be lazy
    private void init() {
        if (attributesByNameAndAdditionalId == null) {
            List<AttributeT> attributes = getAttributes();
            attributesByNameAndAdditionalId = new HashMap<>();
            for (AttributeT attribute : attributes) {
                Map<Long, AttributeT> currentAttributes = attributesByNameAndAdditionalId
                        .computeIfAbsent(attribute.getName(), k-> new HashMap<>());

                AttributeT currentAttribute = currentAttributes
                        .get(attribute.getAdditionalId());

                //ignore older versions TODO: why are we even getting older versions from the db?
                if (currentAttribute == null || isNewRankedHigher(currentAttribute, attribute)) {
                    currentAttributes.put(attribute.getAdditionalId(), attribute);
                }
            }
        }
    }

    public boolean hasAttribute(String name) {
        return getAttributeOrNull(name, 0) != null;
    }

    public String attributeString(String name) {
        return getAttributeValueString(name, "");
    }

    public String getAttributeValueString(String attributeName, String defaultVal) {
        return getAttributeValueString(attributeName, 0, defaultVal);
    }

    public String getAttributeValueString(String attributeName, long additionalId, String defaultVal) {
        AttributeT pa = getAttributeOrNull(attributeName, additionalId);
        return pa == null ? defaultVal : pa.getStringValue();
    }

    public long getAttributeValueLong(String attributeName, long defaultVal) {
        return getAttributeValueLong(attributeName, 0, defaultVal);
    }

    public long getAttributeValueLong(String attributeName, long additionalId, long defaultVal) {
        AttributeT pa = getAttributeOrNull(attributeName, additionalId);
        return pa == null ? defaultVal : pa.getNumericValue();
    }

    public double getAttributeValueReal(String attributeName, long additionalId, double defaultVal) {
        AttributeT pa = getAttributeOrNull(attributeName, additionalId);
        return pa == null ? defaultVal : pa.getRealValue();
    }

    public AttributeT getAttributeOrNull(String attributeName, long additionalId) {
        init();
        final Map<Long, AttributeT> attributesByAdditionalId = attributesByNameAndAdditionalId.get(attributeName);
        if (attributesByAdditionalId == null) {
            return null;
        }
        return attributesByAdditionalId.get(additionalId);
    }

    /**
     * Get newest attribute from among all those that share a name.
     */
    public AttributeT getAttributeOrNull(String attributeName) {
        init();
        final Map<Long, AttributeT> attributesByAdditionalId = attributesByNameAndAdditionalId.get(attributeName);
        if (attributesByAdditionalId == null) {
            return null;
        }

        final Collection<AttributeT> values = attributesByAdditionalId.values();
        AttributeT newestAttributeSeen = null;
        for (AttributeT attribute : values) {
            if (isNewRankedHigher(newestAttributeSeen, attribute)) {
                newestAttributeSeen = attribute;
            }
        }
        return newestAttributeSeen;
    }
}
