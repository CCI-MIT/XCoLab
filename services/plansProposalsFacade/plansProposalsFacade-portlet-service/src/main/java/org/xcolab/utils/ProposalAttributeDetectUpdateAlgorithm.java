package org.xcolab.utils;

import com.ext.portlet.ProposalImpactAttributeKeys;
import com.ext.portlet.model.ProposalAttribute;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kmang on 15/03/15.
 */
public class ProposalAttributeDetectUpdateAlgorithm {

    private final static ProposalAttributeDetectUpdateImpl PROPOSAL_IMPACT_ATTRIBUTE_UPDATE = new ProposalAttributeDetectUpdateImpl() {

        @Override
        public boolean hasBeenUpdated(ProposalAttribute attribute, String attributeName, long additionalId, long numericValue, double realValue) {
            return attribute.getName().equals(attributeName) && attribute.getAdditionalId() == additionalId && attribute.getNumericValue() == numericValue
                    && attribute.getRealValue() != realValue;
        }
    };

    private final static ProposalAttributeDetectUpdateImpl PROPOSAL_OTHER_ATTRIBUTE_UPDATE = new ProposalAttributeDetectUpdateImpl() {

        @Override
        public boolean hasBeenUpdated(ProposalAttribute attribute, String attributeName, long additionalId, long numericValue, double realValue) {
            return attribute.getName().equals(attributeName) && attribute.getAdditionalId() == additionalId;
        }
    };

    private ProposalAttribute proposalAttribute;

    private Map<String, ProposalAttributeDetectUpdateImpl> attributeNameToAlgorithmMap;

    public ProposalAttributeDetectUpdateAlgorithm(ProposalAttribute attribute) {
        this.proposalAttribute = attribute;
        this.attributeNameToAlgorithmMap = new HashMap<>();

        // Init maps
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
        public abstract boolean hasBeenUpdated(ProposalAttribute attribute, String attributeName, long additionalId, long numericValue, double realValue);
    }
}