package org.xcolab.view.pages.proposals.impact.adaptation;

import org.springframework.stereotype.Service;

import org.xcolab.client.contest.pojo.wrapper.ProposalUnversionedAttribute;
import org.xcolab.client.contest.proposals.IProposalAttributeClient;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

import java.util.HashMap;
import java.util.Map.Entry;

@Service
public class AdaptationService {

    private static final String REGION = "REGION";
    private static final String SUB_REGION = "SUB_REGION";
    private static final String PREDICTED_DAMAGES = "PREDICTED_DAMAGES";
    private static final String MIN_PERCENT_REDUCTION = "MIN_PERCENT_REDUCTION";
    private static final String MAX_PERCENT_REDUCTION = "MAX_PERCENT_REDUCTION";

    public AdaptationImpactBean getAdaptationImpactBean(IProposalAttributeClient attributeClient,
            long proposalId) {

        final AttributeHelper attributeHelper = new AttributeHelper(attributeClient, proposalId, 0);

        final HashMap<String, AdaptationValue> values = new HashMap<>();
        values.put(AdaptationCategory.ECONOMIC_DAMAGES.name(),
                getAdaptationValue(attributeHelper, AdaptationCategory.ECONOMIC_DAMAGES));
        values.put(AdaptationCategory.FATALITIES.name(),
                getAdaptationValue(attributeHelper, AdaptationCategory.FATALITIES));
        values.put(AdaptationCategory.SEVERELY_AFFECTED.name(),
                getAdaptationValue(attributeHelper, AdaptationCategory.SEVERELY_AFFECTED));
        values.put(AdaptationCategory.AFFECTED.name(),
                getAdaptationValue(attributeHelper, AdaptationCategory.AFFECTED));
        values.put(AdaptationCategory.CULTURAL_DAMAGES.name(),
                getAdaptationValue(attributeHelper, AdaptationCategory.CULTURAL_DAMAGES));

        final AdaptationImpactBean bean = new AdaptationImpactBean();
        bean.setValues(values);
        bean.setProposalId(proposalId);
        return bean;
    }

    private AdaptationValue getAdaptationValue(AttributeHelper attributeHelper,
            AdaptationCategory category) {
        final AdaptationValue value = new AdaptationValue();
        value.setRegion(attributeHelper.getStringValue(category, REGION));
        value.setSubRegion(attributeHelper.getStringValue(category, SUB_REGION));
        value.setPredictedDamages(attributeHelper.getFloatValue(category, PREDICTED_DAMAGES));
        value.setMinPercentReduction(attributeHelper.getFloatValue(category, MIN_PERCENT_REDUCTION));
        value.setMaxPercentReduction(attributeHelper.getFloatValue(category, MAX_PERCENT_REDUCTION));
        return value;
    }

    public void save(AdaptationImpactBean adaptationImpactBean,
            IProposalAttributeClient attributeClient) {
        AttributeHelper attributeHelper = new AttributeHelper(attributeClient,
                adaptationImpactBean.getProposalId(), adaptationImpactBean.getAuthorUserId());

        for (Entry<String, AdaptationValue> entry : adaptationImpactBean.getValues().entrySet()) {
            final AdaptationCategory category = AdaptationCategory.valueOf(entry.getKey());
            final AdaptationValue value = entry.getValue();
            attributeHelper.saveStringValue(category, REGION, value.getRegion());
            attributeHelper.saveStringValue(category, SUB_REGION, value.getSubRegion());
            attributeHelper.saveFloatValue(category, PREDICTED_DAMAGES, value.getPredictedDamages());
            attributeHelper.saveFloatValue(category, MIN_PERCENT_REDUCTION,
                    value.getMinPercentReduction());
            attributeHelper.saveFloatValue(category, MAX_PERCENT_REDUCTION,
                    value.getMaxPercentReduction());
        }
    }

    private static class AttributeHelper {

        private final IProposalAttributeClient attributeClient;
        private final long proposalId;
        private final long authorUserId;

        AttributeHelper(IProposalAttributeClient attributeClient, long proposalId, long authorUserId) {
            this.attributeClient = attributeClient;
            this.proposalId = proposalId;
            this.authorUserId = authorUserId;
        }

        void saveFloatValue(AdaptationCategory category, String valueType, float value) {
            attributeClient.createOrUpdateUnversionedDoubleAttribute(proposalId,
                    attributeName(category, valueType), authorUserId, value);
        }

        void saveStringValue(AdaptationCategory category, String valueType, String value) {
            attributeClient.createOrUpdateUnversionedStringAttribute(proposalId,
                    attributeName(category, valueType), authorUserId, value);
        }

        float getFloatValue(AdaptationCategory category, String valueType) {
            final ProposalUnversionedAttribute attribute = getAttributeOrNull(category, valueType);
            return attribute != null ? attribute.getRealValue().floatValue() : 0;
        }

        String getStringValue(AdaptationCategory category, String valueType) {
            final ProposalUnversionedAttribute attribute = getAttributeOrNull(category, valueType);
            return attribute != null ? attribute.getStringValue() : "";
        }

        private ProposalUnversionedAttribute getAttributeOrNull(AdaptationCategory category,
                String valueType) {
            try {
                final String name = attributeName(category, valueType);
                return attributeClient.getProposalUnversionedAttribute(proposalId, name);
            } catch (EntityNotFoundException e) {
                return null;
            }
        }

        private String attributeName(AdaptationCategory category, String valueType) {
            return "IMPACT_" + category.name() + "_" + valueType;
        }
    }
}
