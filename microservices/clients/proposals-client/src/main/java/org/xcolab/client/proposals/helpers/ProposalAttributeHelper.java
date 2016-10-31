package org.xcolab.client.proposals.helpers;




import org.xcolab.client.proposals.ProposalAttributeClient;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.attributes.ProposalAttribute;
import org.xcolab.util.EntityGroupingUtil;


import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ProposalAttributeHelper {
    private final Proposal proposal;
    private final int version;
    private Map<String, Map<Long, ProposalAttribute>> attributesByNameAndAdditionalId;


    private ProposalAttributeClient proposalAttributeClient;

    public ProposalAttributeHelper(Proposal proposal, int version, ProposalAttributeClient proposalAttributeClient) {
        this.proposal = proposal;
        this.version = version;
        this.proposalAttributeClient = proposalAttributeClient;
    }

    public ProposalAttributeHelper(Proposal proposal, ProposalAttributeClient proposalAttributeClient) {
        this(proposal, proposal.getCurrentVersion(), proposalAttributeClient);
    }

    //initialization is expensive --> be lazy
    private void init() {
            List<ProposalAttribute> attributes = proposalAttributeClient
                    .getAllProposalAttributes(proposal.getProposalId(),version);
            if (attributesByNameAndAdditionalId == null) {
                attributesByNameAndAdditionalId = new HashMap<>();
                for (ProposalAttribute attribute : attributes) {
                    Map<Long, ProposalAttribute> currentAttributes = EntityGroupingUtil
                            .getInnerMapOrCreate(
                                    attribute.getName(), attributesByNameAndAdditionalId);

                    ProposalAttribute currentAttribute = currentAttributes
                            .get(attribute.getAdditionalId());

                    //ignore older versions TODO: why are we even getting older versions from the db?
                    if (currentAttribute == null || currentAttribute.getVersion() < attribute
                            .getVersion()) {
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

    public double getAttributeValueReal(String attributeName, long additionalId, double defaultVal) {
        ProposalAttribute pa = getAttributeOrNull(attributeName, additionalId);
        return pa == null ? defaultVal : pa.getRealValue();
    }

    public ProposalAttribute getAttributeOrNull(String attributeName, long additionalId) {
        if (attributesByNameAndAdditionalId == null) {
            init();
        }
        final Map<Long, ProposalAttribute> attributesByAdditionalId = attributesByNameAndAdditionalId.get(attributeName);
        if (attributesByAdditionalId == null) {
            return null;
        }
        return attributesByAdditionalId.get(additionalId);
    }

    public ProposalAttribute getAttributeOrNull(String attributeName) {
        if (attributesByNameAndAdditionalId == null) {
            init();
        }
        final Map<Long, ProposalAttribute> attributesByAdditionalId = attributesByNameAndAdditionalId.get(attributeName);
        if (attributesByAdditionalId == null) {
            return null;
        }
        final Collection<ProposalAttribute> values = attributesByAdditionalId.values();
        int highestVersionSeen = 0;
        ProposalAttribute newestAttributeSeen = null;
        for (ProposalAttribute attribute : values) {
            if (attribute.getVersion() > highestVersionSeen) {
                newestAttributeSeen = attribute;
            }
        }
        return newestAttributeSeen;
    }

    public Collection<ProposalAttribute> getAttributesByName(String attributeName) {
        if (attributesByNameAndAdditionalId == null) {
            init();
        }
        final Map<Long, ProposalAttribute> attributesByAdditionalId = attributesByNameAndAdditionalId.get(attributeName);
        if (attributesByAdditionalId != null) {
            return attributesByAdditionalId.values();
        }
        return Collections.emptyList();
    }
}
