package org.xcolab.service.contest.proposal.service.proposalattribute;

import org.xcolab.client.contest.pojo.wrapper.ProposalAttribute;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

//TODO COLAB-2456: duplicated from client
public class ProposalAttributeHelper {

    private final Map<String, Map<Long, ProposalAttribute>> attributesByNameAndAdditionalId;

    public ProposalAttributeHelper(ProposalAttributeHelperData data) {
        this.attributesByNameAndAdditionalId = data.getAttributesByNameAndAdditionalId();
    }

    public boolean hasAttribute(String name) {
        return getAttributeOrNull(name, 0) != null;
    }

    public ProposalAttribute getAttributeOrNull(String attributeName, long additionalId) {
        final Map<Long, ProposalAttribute> attributesByAdditionalId =
                attributesByNameAndAdditionalId.get(attributeName);
        if (attributesByAdditionalId == null) {
            return null;
        }
        return attributesByAdditionalId.get(additionalId);
    }

    public String attributeString(String name) {
        return getAttributeValueString(name, "");
    }

    public String getAttributeValueString(String attributeName, String defaultVal) {
        return getAttributeValueString(attributeName, 0, defaultVal);
    }

    public String getAttributeValueString(String attributeName, long additionalId,
            String defaultVal) {
        ProposalAttribute pa = getAttributeOrNull(attributeName, additionalId);
        return pa == null ? defaultVal : pa.getStringValue();
    }

    public long getAttributeValueLong(String attributeName, long defaultVal) {
        return getAttributeValueLong(attributeName, 0, defaultVal);
    }

    public long getAttributeValueLong(String attributeName, long additionalId, long defaultVal) {
        ProposalAttribute pa = getAttributeOrNull(attributeName, additionalId);
        return pa == null ? defaultVal : pa.getNumericValue();
    }

    public double getAttributeValueReal(String attributeName, long additionalId,
            double defaultVal) {
        ProposalAttribute pa = getAttributeOrNull(attributeName, additionalId);
        return pa == null ? defaultVal : pa.getRealValue();
    }

    public List<ProposalAttribute> getAttributes(String attributeName) {
        final Map<Long, ProposalAttribute> attributesByAdditionalId =
                attributesByNameAndAdditionalId.get(attributeName);
        if (attributesByAdditionalId != null) {
            return new ArrayList<>(attributesByAdditionalId.values());
        }
        return Collections.emptyList();
    }
}
