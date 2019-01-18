package org.xcolab.service.contest.proposal.service.proposalattribute;

import org.xcolab.client.contest.pojo.wrapper.ProposalAttribute;

import java.util.HashMap;
import java.util.Map;

public class ProposalAttributeDetectUpdateAlgorithm {

    private static final ProposalAttributeDetectUpdateImpl PROPOSAL_IMPACT_ATTRIBUTE_UPDATE =
            (attribute, attributeName, additionalId, numericValue, realValue) ->
                    attribute.getName().equals(attributeName) && attribute.getAdditionalId() == additionalId && attribute.getNumericValue() == numericValue
                            && attribute.getRealValue() != realValue;

    private static final ProposalAttributeDetectUpdateImpl PROPOSAL_OTHER_ATTRIBUTE_UPDATE =
            (attribute, attributeName, additionalId, numericValue, realValue) ->
                    attribute.getName().equals(attributeName) && attribute.getAdditionalId() == additionalId;

    private final ProposalAttribute proposalAttribute;

    private final Map<String, ProposalAttributeDetectUpdateImpl> attributeNameToAlgorithmMap;

    public ProposalAttributeDetectUpdateAlgorithm(ProposalAttribute attribute) {
        this.proposalAttribute = attribute;
        this.attributeNameToAlgorithmMap = new HashMap<>();

        // Init maps
        //Note if this is ever re-activated: these names are outdated - they no include the date
        attributeNameToAlgorithmMap.put(ProposalImpactAttributeKeys.IMPACT_REDUCTION, PROPOSAL_IMPACT_ATTRIBUTE_UPDATE);
        attributeNameToAlgorithmMap.put(ProposalImpactAttributeKeys.IMPACT_ADOPTION_RATE, PROPOSAL_IMPACT_ATTRIBUTE_UPDATE);
    }

    public boolean hasBeenUpdated(String attributeName, long additionalId, long numericValue, double realValue) {
        ProposalAttributeDetectUpdateImpl algorithmImpl = attributeNameToAlgorithmMap.get(proposalAttribute.getName());
        if (algorithmImpl != null) {
            return algorithmImpl.hasBeenUpdated(proposalAttribute, attributeName, additionalId, numericValue, realValue);
        }

        // Else use default algorithm
        return PROPOSAL_OTHER_ATTRIBUTE_UPDATE.hasBeenUpdated(proposalAttribute, attributeName, additionalId, numericValue, realValue);
    }

    interface ProposalAttributeDetectUpdateImpl {
        boolean hasBeenUpdated(ProposalAttribute attribute, String attributeName, long additionalId, long numericValue, double realValue);
    }
}
