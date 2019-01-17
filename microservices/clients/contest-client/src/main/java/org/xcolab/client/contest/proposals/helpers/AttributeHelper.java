package org.xcolab.client.contest.proposals.helpers;

import org.xcolab.commons.attributes.Attribute;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public abstract class AttributeHelper<AttributeT extends Attribute> {

    private Map<String, Map<Long, AttributeT>> attributesByNameAndAdditionalId;

    protected abstract Map<String, Map<Long, AttributeT>> loadAttributeData();

    //TODO COLAB-2459: remove this once SCENARIO_ID attribute's weird usage of additionalId is fixed
    //Duplicates service functionality
    protected abstract boolean isNewRankedHigher(AttributeT oldAttribute, AttributeT newAttribute);

    //initialization is expensive --> be lazy
    private void init() {
        if (attributesByNameAndAdditionalId == null) {
            attributesByNameAndAdditionalId = loadAttributeData();
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
            if (newestAttributeSeen == null || isNewRankedHigher(newestAttributeSeen, attribute)) {
                newestAttributeSeen = attribute;
            }
        }
        return newestAttributeSeen;
    }

    public List<AttributeT> getAttributes(String attributeName) {
        init();
        final Map<Long, AttributeT> attributesByAdditionalId =
                attributesByNameAndAdditionalId.get(attributeName);
        if (attributesByAdditionalId != null) {
            return new ArrayList<>(attributesByAdditionalId.values());
        }
        return Collections.emptyList();
    }
}
