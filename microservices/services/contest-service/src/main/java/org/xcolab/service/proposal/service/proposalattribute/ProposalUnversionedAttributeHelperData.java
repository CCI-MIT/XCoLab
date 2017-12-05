package org.xcolab.service.proposal.service.proposalattribute;

import org.xcolab.model.tables.pojos.ProposalUnversionedAttribute;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProposalUnversionedAttributeHelperData {

    private final Map<String, Map<Long, ProposalUnversionedAttribute>>
            attributesByNameAndAdditionalId;

    public ProposalUnversionedAttributeHelperData(List<ProposalUnversionedAttribute> attributes) {
        Map<String, Map<Long, ProposalUnversionedAttribute>> attributesByNameAndAdditionalId =
                new HashMap<>();
        for (ProposalUnversionedAttribute attribute : attributes) {
            Map<Long, ProposalUnversionedAttribute> currentAttributes =
                    attributesByNameAndAdditionalId
                            .computeIfAbsent(attribute.getName(), k -> new HashMap<>());
            currentAttributes.put(attribute.getAdditionalId(), attribute);
        }
        this.attributesByNameAndAdditionalId =
                Collections.unmodifiableMap(attributesByNameAndAdditionalId);
    }

    @SuppressWarnings("unused") // getter for JSON serialization
    public Map<String, Map<Long, ProposalUnversionedAttribute>> getAttributesByNameAndAdditionalId() {
        return attributesByNameAndAdditionalId;
    }
}
