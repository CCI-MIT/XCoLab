package org.xcolab.service.contest.proposal.service.proposalattribute;

import org.xcolab.client.contest.pojo.wrapper.ProposalAttribute;
import org.xcolab.client.contest.pojo.wrapper.ProposalAttributeHelperDataDto;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProposalAttributeHelperData extends ProposalAttributeHelperDataDto {

    public ProposalAttributeHelperData(List<ProposalAttribute> attributes) {
        Map<String, Map<Long, ProposalAttribute>> attributesByNameAndAdditionalId = new HashMap<>();
        for (ProposalAttribute attribute : attributes) {
            Map<Long, ProposalAttribute> currentAttributes = attributesByNameAndAdditionalId
                    .computeIfAbsent(attribute.getName(), k -> new HashMap<>());

            ProposalAttribute currentAttribute = currentAttributes.get(attribute.getAdditionalId());

            if (currentAttribute == null || isNewRankedHigher(currentAttribute, attribute)) {
                currentAttributes.put(attribute.getAdditionalId(), attribute);
            }
        }
        setAttributesByNameAndAdditionalId(
                Collections.unmodifiableMap(attributesByNameAndAdditionalId));
    }

    private boolean isNewRankedHigher(ProposalAttribute oldAttribute,
            ProposalAttribute newAttribute) {
        if (oldAttribute == null || newAttribute == null) {
            throw new IllegalArgumentException("Attributes cannot be null (old, new): "
                    + oldAttribute + ", " + newAttribute);
        }
        return oldAttribute.getVersion() < newAttribute.getVersion();
    }

}
