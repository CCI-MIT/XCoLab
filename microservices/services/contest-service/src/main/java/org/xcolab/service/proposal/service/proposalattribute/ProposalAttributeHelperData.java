package org.xcolab.service.proposal.service.proposalattribute;

import org.xcolab.model.tables.pojos.ProposalAttribute;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProposalAttributeHelperData {

    private final Map<String, Map<Long, ProposalAttribute>> attributesByNameAndAdditionalId;

    public ProposalAttributeHelperData(List<ProposalAttribute> attributes) {
        Map<String, Map<Long, ProposalAttribute>> attributesByNameAndAdditionalId = new HashMap<>();
        for (ProposalAttribute attribute : attributes) {
            Map<Long, ProposalAttribute> currentAttributes = attributesByNameAndAdditionalId
                    .computeIfAbsent(attribute.getName(), k-> new HashMap<>());

            ProposalAttribute currentAttribute = currentAttributes.get(attribute.getAdditionalId());

            if (currentAttribute == null || isNewRankedHigher(currentAttribute, attribute)) {
                currentAttributes.put(attribute.getAdditionalId(), attribute);
            }
        }
        this.attributesByNameAndAdditionalId = Collections
                .unmodifiableMap(attributesByNameAndAdditionalId);
    }

    private boolean isNewRankedHigher(ProposalAttribute oldAttribute,
            ProposalAttribute newAttribute) {
        if (oldAttribute == null || newAttribute == null) {
            throw new IllegalArgumentException("Attributes cannot be null (old, new): "
                    + oldAttribute + ", " + newAttribute);
        }
        return oldAttribute.getVersion() < newAttribute.getVersion();
    }

    @SuppressWarnings("unused") // getter for JSON serialization
    public Map<String, Map<Long, ProposalAttribute>> getAttributesByNameAndAdditionalId() {
        return attributesByNameAndAdditionalId;
    }
}
