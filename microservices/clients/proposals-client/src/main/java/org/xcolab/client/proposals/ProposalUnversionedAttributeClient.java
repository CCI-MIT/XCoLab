package org.xcolab.client.proposals;

import org.xcolab.client.proposals.pojo.ProposalUnversionedAttribute;
import org.xcolab.util.http.caching.CacheKeys;
import org.xcolab.util.http.caching.CacheRetention;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.client.RestService;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public final class ProposalUnversionedAttributeClient {

    private static final RestService proposalService = new RestService("proposals-service");

    private static final RestResource1<ProposalUnversionedAttribute,Long> proposalUnversionedAttributeResource = new RestResource1<>(proposalService,
            "proposalUnversionedAttributes", ProposalUnversionedAttribute.TYPES);

    public static ProposalUnversionedAttribute createProposalUnversionedAttribute(ProposalUnversionedAttribute proposalUnversionedAttribute) {
        return proposalUnversionedAttributeResource.create(proposalUnversionedAttribute).execute();
    }

    public static ProposalUnversionedAttribute getProposalUnversionedAttribute(Long proposalId, String name) {
        return proposalUnversionedAttributeResource.service("getByProposalIdName", ProposalUnversionedAttribute.class)
                .queryParam("proposalId", proposalId)
                .queryParam("name", name)
                .get();

    }

    public static boolean updateProposalUnversionedAttribute(ProposalUnversionedAttribute proposalUnversionedAttribute) {
        return proposalUnversionedAttributeResource.update(proposalUnversionedAttribute, proposalUnversionedAttribute.getId_())
                .execute();
    }

    public static Boolean deleteProposalUnversionedAttribute(Long id_) {
        return proposalUnversionedAttributeResource.delete(id_).execute();
    }

    public static List<ProposalUnversionedAttribute> getProposalUnversionedAttributesByProposalId(Long proposalId) {
        return proposalUnversionedAttributeResource.list()
                .withCache(CacheKeys.withClass(ProposalUnversionedAttribute.class)
                                .withParameter("proposalId", proposalId)
                                .asList(),
                        CacheRetention.MEDIUM)
                .optionalQueryParam("proposalId", proposalId)
                .execute();
    }

    public static void createOrUpdateProposalUnversionedAttribute(long authorId,
                                                                  String attributeValue,
                                                                  String attributeName,
                                                                  Long proposalId) {
        ProposalUnversionedAttribute pua = null;
        pua = getProposalUnversionedAttribute(proposalId, attributeName.toString());
        if (pua == null) {
            pua = new ProposalUnversionedAttribute();
            pua.setCreateAuthorId(authorId);
            pua.setCreateDate(new Timestamp(new Date().getTime()));
            pua.setLastUpdateDate(new Timestamp(new Date().getTime()));
            pua.setName(attributeName);
            pua.setStringValue(attributeValue);
            pua.setProposalId(proposalId);
            createProposalUnversionedAttribute(pua);
        } else {
            pua.setCreateAuthorId(authorId);
            pua.setLastUpdateDate(new Timestamp(new Date().getTime()));
            pua.setStringValue(attributeValue);
            updateProposalUnversionedAttribute(pua);
        }
    }
}
