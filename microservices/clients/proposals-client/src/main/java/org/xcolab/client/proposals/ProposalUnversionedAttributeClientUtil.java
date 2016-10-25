package org.xcolab.client.proposals;

import org.xcolab.client.proposals.pojo.attributes.ProposalUnversionedAttribute;
import org.xcolab.util.http.client.RestService;

import java.util.List;

public final class ProposalUnversionedAttributeClientUtil {

    private static final RestService proposalService = new RestService("proposals-service");

    public static ProposalUnversionedAttributeClient getClient() {
        return client;
    }

    private static final ProposalUnversionedAttributeClient client
            = ProposalUnversionedAttributeClient.fromService(proposalService);

    public static Boolean deleteProposalUnversionedAttribute(Long id_) {
        return client.deleteProposalUnversionedAttribute(id_);
    }

    public static List<ProposalUnversionedAttribute> getProposalUnversionedAttributesByProposalId(
            Long proposalId) {
        return client.getProposalUnversionedAttributesByProposalId(proposalId);
    }

    public static void createOrUpdateProposalUnversionedAttribute(long authorId,
            String attributeValue, String attributeName, Long proposalId) {
        client.createOrUpdateProposalUnversionedAttribute(authorId, attributeValue, attributeName,
                proposalId);
    }

    public static ProposalUnversionedAttribute createProposalUnversionedAttribute(
            ProposalUnversionedAttribute proposalUnversionedAttribute) {
        return client.createProposalUnversionedAttribute(proposalUnversionedAttribute);
    }

    public static ProposalUnversionedAttribute getProposalUnversionedAttribute(
            Long proposalId, String name) {
        return client.getProposalUnversionedAttribute(proposalId, name);
    }

    public static boolean updateProposalUnversionedAttribute(
            ProposalUnversionedAttribute proposalUnversionedAttribute) {
        return client.updateProposalUnversionedAttribute(proposalUnversionedAttribute);
    }
}
