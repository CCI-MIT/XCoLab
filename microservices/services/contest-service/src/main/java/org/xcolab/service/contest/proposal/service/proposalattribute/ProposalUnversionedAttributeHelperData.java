package org.xcolab.service.contest.proposal.service.proposalattribute;

import org.xcolab.client.contest.pojo.wrapper.ProposalUnversionedAttribute;
import org.xcolab.client.contest.pojo.wrapper.ProposalUnversionedAttributeHelperDataDto;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProposalUnversionedAttributeHelperData
        extends ProposalUnversionedAttributeHelperDataDto {

    public ProposalUnversionedAttributeHelperData(List<ProposalUnversionedAttribute> attributes) {
        Map<String, Map<Long, ProposalUnversionedAttribute>> attributesByNameAndAdditionalId =
                new HashMap<>();
        for (ProposalUnversionedAttribute attribute : attributes) {
            Map<Long, ProposalUnversionedAttribute> currentAttributes =
                    attributesByNameAndAdditionalId
                            .computeIfAbsent(attribute.getName(), k -> new HashMap<>());
            currentAttributes.put(attribute.getAdditionalId(), attribute);
        }
        setAttributesByNameAndAdditionalId(
                Collections.unmodifiableMap(attributesByNameAndAdditionalId));
    }
}
